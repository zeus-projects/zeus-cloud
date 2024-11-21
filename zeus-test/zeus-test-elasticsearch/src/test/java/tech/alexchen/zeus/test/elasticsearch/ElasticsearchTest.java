package tech.alexchen.zeus.test.elasticsearch;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import tech.alexchen.zeus.test.elasticsearch.entity.Ecs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexchen
 */
@SpringBootTest(classes = ZeusTestElasticsearchApplication.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    void createIndex() {
        // 获取索引操作对象
        IndexOperations indexOperations = template.indexOps(Ecs.class);

        // 是否存在，存在则删除
        if (indexOperations.exists()) {
            indexOperations.delete();
        }

        // 创建索引
        indexOperations.create();

        // createMapping 根据实体类获取映射关系
        Document mapping = indexOperations.createMapping(Ecs.class);

        // putMapping 把映射关系添加到索引中
        indexOperations.putMapping(mapping);
    }

    @Test
    void deleteIndex() {
        IndexOperations indexOperations = template.indexOps(Ecs.class);
        boolean delete = indexOperations.delete();
        System.out.println(delete);
    }

    @Test
    void insert(){
        Ecs ecs = Ecs.builder()
                .id(IdUtil.getSnowflakeNextId())
                .name("vm-1")
                .ipv4("127.0.0.1")
                .desc("4c8g云服务器")
                .dept("北京这是一个有趣的责任有限公司")
                .build();
        Ecs save = template.save(ecs);
        System.out.println(save);
    }

    @Test
    void batchInsert(){
        List<String> deptList = CollectionUtil.newArrayList("研发部", "产品部", "测试中心", "运维中心", "经理室");
        List<Ecs> ecsList = new ArrayList<>();
        for (int i = 0; i < 255; i++) {
            Ecs ecs = Ecs.builder()
                    .id(IdUtil.getSnowflakeNextId())
                    .name(StrUtil.format("vm-{}", i))
                    .ipv4(StrUtil.format("139.159.230.{}", i))
                    .desc("")
                    .dept(RandomUtil.randomEle(deptList))
                    .build();
            ecsList.add(ecs);
        }
        template.save(ecsList);
    }

}

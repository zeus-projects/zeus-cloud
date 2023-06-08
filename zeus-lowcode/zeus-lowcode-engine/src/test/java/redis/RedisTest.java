package redis;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ActiveProfiles;
import tech.alexchen.zeus.lowcode.engine.ZeusLowcodeEngineApplication;
import tech.alexchen.zeus.lowcode.engine.crud.domain.Column;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author alexchen
 */
@ActiveProfiles("test")
//@Import(RedisMessageListenerConfig.class)
@SpringBootTest(classes = ZeusLowcodeEngineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void valueTest() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.setIfAbsent("zeus", "111");
        System.out.println(operations.get("zeus"));
        Assertions.assertEquals("111", operations.get("zeus"));

        operations.setIfAbsent("zeus", "222");
        System.out.println(operations.get("zeus"));
        Assertions.assertEquals("111", operations.get("zeus")); // 不会覆盖

        operations.set("zeus", "333");
        System.out.println(operations.get("zeus"));
        Assertions.assertEquals("333", operations.get("zeus")); // 会覆盖

        operations.setIfPresent("zeus", "444");
        System.out.println(operations.get("zeus"));
        Assertions.assertEquals("444", operations.get("zeus")); // 会覆盖

        redisTemplate.delete("zeus");
        System.out.println(operations.get("zeus"));
    }

    @Test
    void prefixTest() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("zeus:string", "test");
        Assertions.assertEquals("test", operations.get("zeus:string"));

        Column column = new Column();
        column.setName("name");
        column.setId("id");
        column.setNotNull(1);
        operations.set("zeus:object", column);
        Assertions.assertEquals(column, operations.get("zeus:object"));

        redisTemplate.expire("zeus:object", 1, TimeUnit.MINUTES);
    }

    @Test
    void listTest() {
        String key = "zeus:list";
        ListOperations<String, Object> operations = redisTemplate.opsForList();
        operations.leftPush(key, "java");
        operations.leftPush(key, "go");
        operations.leftPush(key, "python");
        operations.leftPush(key, "javascript");
        operations.leftPush(key,  "null");
        Object zeus = operations.index(key, 2);
        System.out.println(zeus);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    @Test
    void setTest() {
        String key = "zeus:set";
        SetOperations<String, Object> operations = redisTemplate.opsForSet();
        operations.add(key, "java");
        operations.add(key, "python");
        operations.add(key, "go");
        operations.add(key, "java");
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    @Test
    void zsetTest() {
        String key = "zeus:zset";
        ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
        operations.add(key, "java", 1);
        operations.add(key, "python", 3);
        operations.add(key, "go",2);
        operations.add(key, "java",4); // 会重设 score
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    @Test
    void hashTest() {
        String key = "zeus:hash";
        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
        operations.put(key, "a","java");
        operations.put(key, "b","python");
        operations.put(key, "c","go");
        operations.put(key, "d","java");

        Column column = new Column();
        column.setName("name");
        column.setId("id");
        column.setNotNull(1);
        operations.put(key, "column",column);

        Assertions.assertEquals(operations.get(key, "column"), column);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    @Test
    void topicTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            redisTemplate.convertAndSend("TEST", "zeus:" + i);
            Thread.sleep(1000L);
        }
    }
}



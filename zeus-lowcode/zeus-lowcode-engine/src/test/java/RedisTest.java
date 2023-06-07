import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.test.context.ActiveProfiles;
import tech.alexchen.zeus.lowcode.engine.ZeusLowcodeEngineApplication;
import tech.alexchen.zeus.lowcode.engine.crud.domain.Column;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author alexchen
 */
@ActiveProfiles("test")
@Import(RedisConfiguration.class)
@SpringBootTest(classes = ZeusLowcodeEngineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void setTest() {
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
        ListOperations<String, Object> operations = redisTemplate.opsForList();
        operations.leftPush("zeus:list", "java");
        operations.leftPush("zeus:list", "go");
        operations.leftPush("zeus:list", "python");
        operations.leftPush("zeus:list", "javascript");
        operations.leftPush("zeus:list",  "null");
        Object zeus = operations.index("zeus:list", 2);
        System.out.println(zeus);
        redisTemplate.expire("zeus:list", 1, TimeUnit.MINUTES);
    }

    @Test
    void topicTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            redisTemplate.convertAndSend("TEST", "zeus:" + i);
            Thread.sleep(1000L);
        }
    }

}



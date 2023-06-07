import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import tech.alexchen.zeus.lowcode.engine.ZeusLowcodeEngineApplication;


/**
 * @author alexchen
 */
@SpringBootTest(classes = ZeusLowcodeEngineApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    void test() {
        template.opsForValue().set("hello", "world");
        System.out.println(template.opsForValue().get("hello"));
    }
}

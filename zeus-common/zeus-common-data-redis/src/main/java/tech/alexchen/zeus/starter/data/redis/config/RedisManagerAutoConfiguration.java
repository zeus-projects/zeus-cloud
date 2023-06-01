package tech.alexchen.zeus.starter.data.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 自动配置
 *
 * @author alexchen
 */
@Configuration
public class RedisManagerAutoConfiguration {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 【重要】设置开启事务支持，需要事务时启用
//        template.setEnableTransactionSupport(true);
        // 设置 RedisConnection 工厂
        template.setConnectionFactory(factory);
        // 使用 String 序列化方式，序列化 KEY
        template.setKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }
}

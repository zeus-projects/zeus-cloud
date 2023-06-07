package tech.alexchen.zeus.common.data.redis.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 自动配置
 *
 * @author alexchen
 */
//@Configuration
@AutoConfiguration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnBean({ RedisConnectionFactory.class })
public class ZeusRedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 【重要】设置开启事务支持，需要事务时启用
//        template.setEnableTransactionSupport(true);
        // 设置 RedisConnection 工厂
        template.setConnectionFactory(redisConnectionFactory);
        // 使用 String 序列化方式，序列化 KEY
        template.setKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }
}

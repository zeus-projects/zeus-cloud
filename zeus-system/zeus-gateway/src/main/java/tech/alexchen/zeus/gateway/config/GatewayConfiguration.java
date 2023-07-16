package tech.alexchen.zeus.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author alexchen
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

    /**
     * 配置限流器 KeyResolver
     * <a href="https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-requestratelimiter-gatewayfilter-factory">参考资料</a>
     */
    @Bean
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono
                .just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }
}

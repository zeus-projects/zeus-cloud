package tech.alexchen.zeus.common.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.alexchen.zeus.common.feign.core.InnerHeaderRequestInterceptor;

/**
 * @author alexchen
 */
@Configuration(proxyBeanMethods = false)
public class OpenFeignConfig {

    @Bean
    InnerHeaderRequestInterceptor innerHeaderRequestInterceptor() {
        return new InnerHeaderRequestInterceptor();
    }

}

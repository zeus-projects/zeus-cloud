package tech.alexchen.zeus.common.security.resource.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tech.alexchen.zeus.common.security.resource.component.InnerAspect;
import tech.alexchen.zeus.common.security.resource.component.PermissionService;

/**
 * @author alexchen
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@EnableConfigurationProperties(ResourceServerProperties.class)
public class ResourceServerAutoConfiguration {

    private final ResourceServerProperties properties;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 获取配置的不需要认证的接口路径列表，配置为 permitAll
        AntPathRequestMatcher[] requestMatchers = properties.getPermitUrls()
                .stream()
                .map(AntPathRequestMatcher::new)
                .toList()
                .toArray(new AntPathRequestMatcher[]{});

        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(token ->
                        token.introspectionClientCredentials(properties.getOpaqueToken().getClientId(), properties.getOpaqueToken().getClientSecret())
                                .introspectionUri(properties.getOpaqueToken().getIntrospectionUri())));
        return http.build();
    }

    @Bean("pms")
    public PermissionService permissionService() {
        return new PermissionService();
    }

    @Bean
    public InnerAspect innerAspect() {
        return new InnerAspect();
    }
}

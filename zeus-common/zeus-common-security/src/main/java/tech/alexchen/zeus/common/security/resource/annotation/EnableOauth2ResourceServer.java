package tech.alexchen.zeus.common.security.resource.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import tech.alexchen.zeus.common.security.resource.config.ResourceServerAutoConfiguration;

import java.lang.annotation.*;

/**
 * 启用资源服务器配置
 *
 * @author alexchen
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableMethodSecurity
@Import(value = {ResourceServerAutoConfiguration.class})
public @interface EnableOauth2ResourceServer {

}

package tech.alexchen.zeus.common.security.resource.annotation;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public @interface EnableOauth2ResourceServer {

}

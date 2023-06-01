package tech.alexchen.zeus.common.security.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;

import java.lang.annotation.*;

/**
 * 启用资源服务器
 *
 * @author alexchen
 * @date 2023/2/18
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerConfiguration.class})
public @interface EnableZeusResourceServer {


}

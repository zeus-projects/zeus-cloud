package tech.alexchen.zeus.common.security.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import tech.alexchen.zeus.common.security.config.ResourceServerConfiguration;

import java.lang.annotation.*;

/**
 * 启用资源服务器
 *
 * @author alexchen
 * @date 2023/2/18
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableResourceServer // 开启资源服务器自动配置
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解鉴权
@Import({ResourceServerConfiguration.class})
public @interface EnableZeusResourceServer {

}

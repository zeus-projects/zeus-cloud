package tech.alexchen.zeus.starter.swagger.annotation;

import org.springframework.context.annotation.Import;
import tech.alexchen.zeus.starter.swagger.config.SwaggerConfiguration;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerConfiguration.class })
public @interface EnableZeusSwagger {

}

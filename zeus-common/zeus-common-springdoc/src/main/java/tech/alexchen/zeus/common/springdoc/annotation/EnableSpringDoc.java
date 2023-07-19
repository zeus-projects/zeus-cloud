package tech.alexchen.zeus.common.springdoc.annotation;

import org.springframework.context.annotation.Import;
import tech.alexchen.zeus.common.springdoc.config.SpringDocConfiguration;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ SpringDocConfiguration.class })
public @interface EnableSpringDoc {

}

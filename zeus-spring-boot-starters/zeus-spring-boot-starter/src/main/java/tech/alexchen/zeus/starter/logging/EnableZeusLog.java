package tech.alexchen.zeus.starter.logging;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({LogAutoConfiguration.class})
public @interface EnableZeusLog {

}

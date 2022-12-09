package tech.alexchen.zeus.starter.apiversion;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ ApiVersionConfiguration.class})
public @interface EnableApiVersion {

}

package tech.alexchen.zeus.starter.logging;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

}

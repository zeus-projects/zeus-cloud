package tech.alexchen.zeus.common.log.annotation;

import java.lang.annotation.*;

/**
 * 方法执行计时器
 *
 * @author alexchen
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimerLog {

    String value() default "";
}

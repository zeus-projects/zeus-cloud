package tech.alexchen.zeus.common.security.resource.annotation;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Inner {

    /**
     * 是否仅可内部访问，默认为 true
     */
    boolean value() default true;

}

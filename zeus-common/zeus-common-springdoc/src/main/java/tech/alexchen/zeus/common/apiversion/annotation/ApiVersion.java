package tech.alexchen.zeus.common.apiversion.annotation;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 自定义 API 版本注解
 *
 * @author alexchen
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * 版本名称
     */
    String value();
}

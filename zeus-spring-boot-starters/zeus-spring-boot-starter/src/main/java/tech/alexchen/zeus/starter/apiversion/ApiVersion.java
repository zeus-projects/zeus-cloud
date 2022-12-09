package tech.alexchen.zeus.starter.apiversion;

/**
 * 自定义 @ApiVersion 注解
 */

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

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

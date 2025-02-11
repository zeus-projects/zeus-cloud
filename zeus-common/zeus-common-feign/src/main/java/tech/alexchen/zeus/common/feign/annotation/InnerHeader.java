package tech.alexchen.zeus.common.feign.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于自动给内部调用的 feign 接口请求添加 inner 请求头，而无需手动为 feign 接口标注
 *
 * @author alexchen
 * @since 2025-02-11 10:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerHeader {

}

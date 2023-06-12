package tech.alexchen.zeus.common.feign.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * @author alexchen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableFeignClients(basePackages = "tech.alexchen.zeus")
public @interface EnableZeusFeignClient {

}

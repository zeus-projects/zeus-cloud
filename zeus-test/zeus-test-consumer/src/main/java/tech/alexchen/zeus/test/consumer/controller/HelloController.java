package tech.alexchen.zeus.test.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.test.consumer.feign.ProducerFeign;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
public class HelloController {

    @Resource
    private ProducerFeign feign;
    @GetMapping("/hello")
    public String hello() {
        return feign.hello();
    }
}

package tech.alexchen.zeus.test.consumer.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/feign")
public class HelloController {

    @Resource
    private ProducerFeign feign;

    @GetMapping("/hello")
    public String hello() {
        return feign.hello();
    }
}

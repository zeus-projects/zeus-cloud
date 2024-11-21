package tech.alexchen.zeus.test.kafka.consumer.feign;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

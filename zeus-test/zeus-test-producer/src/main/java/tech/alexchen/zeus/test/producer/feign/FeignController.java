package tech.alexchen.zeus.test.producer.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @GetMapping("/hello")
    public String hello() {
        return "Producer say: Hello!";
    }

    @GetMapping("/limit")
    public String limit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Producer limit";
    }
}

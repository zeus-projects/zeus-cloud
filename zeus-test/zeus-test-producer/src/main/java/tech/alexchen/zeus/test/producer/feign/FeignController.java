package tech.alexchen.zeus.test.producer.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;

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
    @GetMapping("/hello2")
    public R<String> hello2() {
        return R.ok("hello2");
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

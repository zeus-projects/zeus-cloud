package tech.alexchen.zeus.test.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 */
@RestController
public class ProducerController {

    @GetMapping("/hello")
    public String hello() {
        return "Producer say: Hello!";
    }
}

package tech.alexchen.zeus.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 * @date 2023/3/26
 */
@RestController("/test")
public class TestController {

    @GetMapping("/info")
    public String test() {
        return "auth test info";
    }
}

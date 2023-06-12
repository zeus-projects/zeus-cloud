package tech.alexchen.zeus.upms.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 */
@RestController
public class TestController {

    @Value("${server.port}")
    Integer serverPort;


    @GetMapping("/test")
    public String test() {
        return StrUtil.format("current server: {}", serverPort);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "user";
    }
}

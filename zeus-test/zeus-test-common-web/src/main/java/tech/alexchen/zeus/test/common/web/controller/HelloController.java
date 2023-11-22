package tech.alexchen.zeus.test.common.web.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.log.annotation.TimerLog;
import tech.alexchen.zeus.test.common.web.pojo.UserDTO;
import tech.alexchen.zeus.test.common.web.service.HelloService;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
public class HelloController {

    @Resource
    private HelloService service;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @TimerLog("HelloController-timer")
    @GetMapping("/timer")
    public String timer() {
        try {
            Thread.sleep(100);
            service.doSomething();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "timer";
    }



}


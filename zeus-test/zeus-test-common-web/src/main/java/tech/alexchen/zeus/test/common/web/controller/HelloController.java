package tech.alexchen.zeus.test.common.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.log.annotation.TimerLog;
import tech.alexchen.zeus.test.common.web.service.HelloService;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/common/web")
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

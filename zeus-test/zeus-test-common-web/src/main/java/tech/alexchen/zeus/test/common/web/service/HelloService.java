package tech.alexchen.zeus.test.common.web.service;

import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.log.annotation.TimerLog;

/**
 * @author alexchen
 */
@Service
public class HelloService {

    @TimerLog("doSomething")
    public void doSomething() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

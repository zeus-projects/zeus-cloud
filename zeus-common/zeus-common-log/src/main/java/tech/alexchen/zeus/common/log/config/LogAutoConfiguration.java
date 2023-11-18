package tech.alexchen.zeus.common.log.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import tech.alexchen.zeus.common.log.aop.SysLogAspect;
import tech.alexchen.zeus.common.log.aop.TimerLogAspect;
import tech.alexchen.zeus.common.log.event.SysLogListener;

/**
 * @author alexchen
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener();
    }

    @Bean
    public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
        return new SysLogAspect(publisher);
    }

    @Bean
    public TimerLogAspect timerLogAspect() {
        return new TimerLogAspect();
    }
}

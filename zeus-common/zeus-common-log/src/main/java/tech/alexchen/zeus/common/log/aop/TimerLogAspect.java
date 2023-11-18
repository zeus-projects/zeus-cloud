package tech.alexchen.zeus.common.log.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import tech.alexchen.zeus.common.log.annotation.TimerLog;

/**
 * @author alexchen
 */
@Slf4j
@Aspect
public class TimerLogAspect {

    @Around("@annotation(timerLog)")
    public Object around(ProceedingJoinPoint point, TimerLog timerLog) {
        String timerName = timerLog.value();
        if (StrUtil.isBlank(timerName)) {
            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();
            timerName = StrUtil.format("{} - {}", className, methodName);
        }
        Object result;
        // 开始计时
        TimeInterval timer = DateUtil.timer();
        timer.start();
        log.info(">>> TimerLog {} started", timerName);
        try {
            //执行方法
            result = point.proceed();
            log.info(">>> TimerLog {} end, interval: {} ms", timerName, timer.interval());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}

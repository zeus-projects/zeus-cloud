package tech.alexchen.zeus.common.log.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import tech.alexchen.zeus.common.log.bean.SysLogInfo;

/**
 * @author alexchen
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    @Async
    @EventListener(SysLogEvent.class)
    public void handleLog(SysLogEvent event) {
        SysLogInfo logInfo = event.getSysLogInfo();
        log.debug("{} 执行耗时 {} ms", logInfo.getTitle(), logInfo.getTime());
    }
}

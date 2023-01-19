package tech.alexchen.zeus.starter.log.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.alexchen.zeus.starter.log.bean.SysLogInfo;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

    private final SysLogInfo sysLogInfo;
}

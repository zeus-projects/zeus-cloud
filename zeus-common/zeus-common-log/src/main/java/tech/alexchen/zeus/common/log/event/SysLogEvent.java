package tech.alexchen.zeus.common.log.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.alexchen.zeus.common.log.bean.SysLogInfo;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

    private final SysLogInfo sysLogInfo;
}

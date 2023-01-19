package tech.alexchen.zeus.starter.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志类型枚举值
 *
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum LogTypeEnum {

    NORMAL("0", "正常日志"),
    ERROR("1", "错误日志");

    private String typeId;

    private String typeName;
}

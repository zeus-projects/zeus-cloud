package tech.alexchen.zeus.starter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

}

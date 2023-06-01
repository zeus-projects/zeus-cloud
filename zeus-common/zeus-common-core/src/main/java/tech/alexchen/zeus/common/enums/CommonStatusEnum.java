package tech.alexchen.zeus.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    /**
     * 可用状态
     */
    ENABLE(0, "可用"),
    /**
     * 不可用状态
     */
    DISABLE(1, "不可用");

    /**
     * 状态编码
     */
    private final Integer status;

    /**
     * 状态描述
     */
    private final String name;

}

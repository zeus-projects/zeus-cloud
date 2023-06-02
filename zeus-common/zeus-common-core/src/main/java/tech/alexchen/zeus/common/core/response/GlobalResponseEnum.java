package tech.alexchen.zeus.common.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * R 返回信息枚举
 *
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum GlobalResponseEnum implements ResponseEnum {

    SUCCESS("00000", "success"),
    FAIL("99999", "failed");

    public static final List<GlobalResponseEnum> RESPONSE_ENUM_ALL =
            Collections.unmodifiableList(Arrays.asList(GlobalResponseEnum.values()));

    /**
     * 状态码
     */
    private final String code;

    /**
     * 信息
     */
    private final String message;

}

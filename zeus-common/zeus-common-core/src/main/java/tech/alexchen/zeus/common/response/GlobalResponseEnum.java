package tech.alexchen.zeus.common.response;

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
public enum GlobalResponseEnum implements ResponseEnum{

    SUCCESS(0, "success"),
    FAIL(1, "failed");

    public static final List<GlobalResponseEnum> RESPONSE_ENUM_ALL = Collections.unmodifiableList(Arrays.asList(GlobalResponseEnum.values()));

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

}

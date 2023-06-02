package tech.alexchen.zeus.common.core.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回包装
 *
 * @author alexchen
 */
@Data
@Builder
public class R<T> implements Serializable{

    /**
     * 13 时间戳
     */
    private Long timestamp;

    /**
     * 状态码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> R<T> build(String code, String message, T data) {
        return R.<T>builder()
                .timestamp(System.currentTimeMillis())
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> R<T> build(ResponseEnum responseEnum, T data) {
        return R.<T>builder()
                .timestamp(System.currentTimeMillis())
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .data(data)
                .build();
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.build(GlobalResponseEnum.SUCCESS.getCode(),
                GlobalResponseEnum.SUCCESS.getMessage(),
                data);
    }

    public static <T> R<T> fail(String message) {
        return fail(message, null);
    }

    public static <T> R<T> fail(String message, T data) {
        return R.build(GlobalResponseEnum.FAIL.getCode(), message, data);
    }


}

package tech.alexchen.zeus.common.core.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

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
                .timestamp(Instant.now().toEpochMilli())
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> R<T> build(ResponseCode responseCode, T data) {
        return R.<T>builder()
                .timestamp(Instant.now().toEpochMilli())
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.build(GlobalResponseEnum.SUCCESS.getResponse(), data);
    }

    public static <T> R<T> fail(String message) {
        return fail(message, null);
    }

    public static <T> R<T> fail(String message, T data) {
        return R.build(GlobalResponseEnum.FAILED.getCode(), message, data);
    }
}

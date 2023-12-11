package tech.alexchen.zeus.common.core.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * 统一返回包装
 *
 * @author alexchen
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

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

    public static R<Boolean> bool(boolean bool) {
        return bool ? R.ok(true) : R.fail(null, false);
    }

    public static R<Boolean> bool(boolean bool, String message) {
        return bool ? R.ok(true) : R.fail(message, false);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return GlobalResponseEnum.SUCCESS.getCode().equals(this.code);
    }

    @JsonIgnore
    public boolean isFailed() {
        return !GlobalResponseEnum.SUCCESS.getCode().equals(this.code);
    }
}

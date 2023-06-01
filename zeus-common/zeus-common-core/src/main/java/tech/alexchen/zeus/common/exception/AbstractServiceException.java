package tech.alexchen.zeus.common.exception;

import lombok.*;

/**
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractServiceException extends RuntimeException {

    /**
     * 业务错误码
     *
     * @see
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    public AbstractServiceException() {}

    public AbstractServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AbstractServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}

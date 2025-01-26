package tech.alexchen.zeus.common.core.exception;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * 带响应的异常
 *
 * @author alexchen
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponsiveRuntimeException extends RuntimeException {

    private final ResponseCode response;

    public String getErrorCode() {
        return response.getCode();
    }

    public String getErrorMessage() {
        return response.getMessage();
    }
}

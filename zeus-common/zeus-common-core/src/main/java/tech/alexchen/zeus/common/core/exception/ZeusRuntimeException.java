package tech.alexchen.zeus.common.core.exception;

import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 */
@EqualsAndHashCode(callSuper = true)
public class ZeusRuntimeException extends RuntimeException {

    private final ResponseCode response;

    public ZeusRuntimeException(ResponseCode response) {
        this.response = response;
    }

    public ZeusRuntimeException(String code, String message) {
        this.response = new ResponseCode(code, message);
    }

    public ResponseCode getResponse() {
        return this.response;
    }

    public String getCode() {
        return response.getCode();
    }

    public String getMessage() {
        return response.getMessage();
    }
}

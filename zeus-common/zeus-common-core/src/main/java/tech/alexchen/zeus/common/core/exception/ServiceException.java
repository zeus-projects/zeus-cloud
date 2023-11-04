package tech.alexchen.zeus.common.core.exception;

import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;


/**
 * 业务异常
 *
 * @author alexchen
 */
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends ZeusRuntimeException {


    public ServiceException(String code, String message) {
        super(code, message);
    }

    public ServiceException(ResponseCode responseCode) {
        super(responseCode);
    }
}

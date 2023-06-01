package tech.alexchen.zeus.common.exception;

import lombok.*;


/**
 * 业务异常
 *
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends AbstractServiceException {

    public ServiceException() {}

    public ServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ServiceException(String code, String message) {
        super(code, message);
    }
}

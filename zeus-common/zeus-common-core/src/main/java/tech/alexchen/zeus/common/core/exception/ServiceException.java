package tech.alexchen.zeus.common.core.exception;

import lombok.*;
import tech.alexchen.zeus.common.core.response.ResponseCode;
import tech.alexchen.zeus.common.core.response.Responsive;


/**
 * 业务异常
 *
 * @author alexchen
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException implements Responsive {


    private final ResponseCode response;

    public ServiceException(String code, String message) {
        this.response = new ResponseCode(code, message);
    }

    public <E extends Responsive> ServiceException(E e) {
        this.response = e.getResponse();
    }

    @Override
    public ResponseCode getResponse() {
        return response;
    }
}

package tech.alexchen.zeus.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;
import tech.alexchen.zeus.common.core.response.Responsive;

/**
 * @author alexchen
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ThirdPartyServiceException extends RuntimeException implements Responsive {

    private final ResponseCode response;

    public ThirdPartyServiceException(String code, String message) {
        this.response = new ResponseCode(code, message);
    }

    public <E extends Responsive> ThirdPartyServiceException(E e) {
        this.response = e.getResponse();
    }
    @Override
    public ResponseCode getResponse() {
        return response;
    }
}

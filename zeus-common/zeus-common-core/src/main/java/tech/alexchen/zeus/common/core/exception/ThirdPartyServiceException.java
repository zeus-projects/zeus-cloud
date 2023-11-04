package tech.alexchen.zeus.common.core.exception;

import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 */
@EqualsAndHashCode(callSuper = true)
public final class ThirdPartyServiceException extends ZeusRuntimeException {

    public ThirdPartyServiceException(String code, String message) {
        super(code, message);
    }

    public ThirdPartyServiceException(ResponseCode responseCode) {
        super(responseCode);
    }
}

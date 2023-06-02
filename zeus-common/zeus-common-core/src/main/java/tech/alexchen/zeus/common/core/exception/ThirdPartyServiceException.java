package tech.alexchen.zeus.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ThirdPartyServiceException extends AbstractServiceException {

    public ThirdPartyServiceException() {}

    public ThirdPartyServiceException(String code, String message) {
        super(code, message);
    }

    public ThirdPartyServiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}

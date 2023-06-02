package tech.alexchen.zeus.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务器异常
 *
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServerException extends AbstractServiceException {

    public ServerException() {}

    public ServerException(String code, String message) {
        super(code, message);
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}

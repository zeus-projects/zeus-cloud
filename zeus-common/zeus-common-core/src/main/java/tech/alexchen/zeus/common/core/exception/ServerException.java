package tech.alexchen.zeus.common.core.exception;

import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * 服务器异常
 *
 * @author alexchen
 */
@EqualsAndHashCode(callSuper = true)
public final class ServerException extends ZeusRuntimeException {

    public ServerException(String code, String message) {
        super(code, message);
    }

    public ServerException(ResponseCode responseCode) {
        super(responseCode);
    }

}

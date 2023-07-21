package tech.alexchen.zeus.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.core.response.ResponseCode;
import tech.alexchen.zeus.common.core.response.Responsive;

/**
 * 服务器异常
 *
 * @author alexchen
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ServerException extends RuntimeException implements Responsive {

    private final ResponseCode response;

    public ServerException(String code, String message) {
        this.response = new ResponseCode(code, message);
    }

    public <E extends Responsive> ServerException(E e) {
        this.response = e.getResponse();
    }

    @Override
    public ResponseCode getResponse() {
        return response;
    }
}

package tech.alexchen.zeus.common.core.response.refactor;

import lombok.AllArgsConstructor;
import tech.alexchen.zeus.common.core.response.Responsive;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 */
@AllArgsConstructor
public class Response extends RuntimeException implements Responsive {

    private final ResponseCode response;

    @Override
    public ResponseCode getResponse() {
        return response;
    }

}

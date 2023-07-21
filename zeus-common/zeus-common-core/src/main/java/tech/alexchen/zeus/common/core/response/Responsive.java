package tech.alexchen.zeus.common.core.response;

/**
 * @author alexchen
 */
public interface Responsive {

    ResponseCode getResponse();

    default String getCode() {
        return getResponse().getCode();
    }

    default String getMessage() {
        return getResponse().getMessage();
    }
}

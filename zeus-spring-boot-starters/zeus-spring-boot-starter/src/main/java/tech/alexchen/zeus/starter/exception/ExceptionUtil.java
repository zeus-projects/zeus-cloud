package tech.alexchen.zeus.starter.exception;

/**
 * 异常信息工具类
 *
 * @author alexchen
 */
public class ExceptionUtil {

    public static RuntimeException newException(ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

}

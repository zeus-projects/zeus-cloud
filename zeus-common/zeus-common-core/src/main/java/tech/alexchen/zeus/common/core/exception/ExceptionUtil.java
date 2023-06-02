package tech.alexchen.zeus.common.core.exception;

import cn.hutool.core.util.StrUtil;
import tech.alexchen.zeus.common.core.constants.GlobalErrorCode;

/**
 * 异常信息工具类
 *
 * @author alexchen
 */
public class ExceptionUtil {

    public static RuntimeException exception(ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

    public static RuntimeException exception(String messageTemplate, Object... params) {
        return new ServiceException(GlobalErrorCode.CLIENT_ERROR.getCode(),
                StrUtil.format(messageTemplate, params));
    }

}

package tech.alexchen.zeus.common.core.utils;

import tech.alexchen.zeus.common.core.exception.ServerException;
import tech.alexchen.zeus.common.core.exception.ServiceException;
import tech.alexchen.zeus.common.core.exception.ThirdPartyServiceException;
import tech.alexchen.zeus.common.core.response.GlobalResponseEnum;
import tech.alexchen.zeus.common.core.response.ResponseCode;
import tech.alexchen.zeus.common.core.response.Responsive;

import java.util.function.Supplier;

/**
 * 异常信息工具类
 *
 * @author alexchen
 */
public class ExceptionUtil {

    public static RuntimeException service(Responsive responsive) {
        return new ServiceException(responsive);
    }
    public static RuntimeException service(ResponseCode errorCode) {
        return new ServiceException(errorCode);
    }

    public static RuntimeException service(Supplier<ResponseCode> supplier) {
        return service(supplier.get());
    }

    public static RuntimeException service() {
        return service(GlobalResponseEnum.SERVICE_ERROR);
    }


    public static RuntimeException server(Responsive responsive) {
        return new ServiceException(responsive);
    }

    public static RuntimeException server(ResponseCode errorCode) {
        return new ServerException(errorCode);
    }

    public static RuntimeException server(Supplier<ResponseCode> supplier) {
        return server(supplier.get());
    }

    public static RuntimeException server() {
        return server(GlobalResponseEnum.SERVER_ERROR);
    }


    public static RuntimeException thirdPartyService(Responsive responsive) {
        return new ServiceException(responsive);
    }
    public static RuntimeException thirdPartyService(ResponseCode errorCode) {
        return new ThirdPartyServiceException(errorCode);
    }

    public static RuntimeException thirdPartyService(Supplier<ResponseCode> supplier) {
        return thirdPartyService(supplier.get());
    }

    public static RuntimeException thirdPartyService() {
        return thirdPartyService(GlobalResponseEnum.THIRD_PARTY_SERVICE_ERROR);
    }
}

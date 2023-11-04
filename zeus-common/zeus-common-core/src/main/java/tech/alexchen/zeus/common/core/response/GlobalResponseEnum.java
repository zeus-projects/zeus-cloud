package tech.alexchen.zeus.common.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum GlobalResponseEnum {

    /**
     * 正常
     */
    SUCCESS(new ResponseCode("00000", "操作成功")),
    /**
     * 全局通用错误
     */
    FAILED(new ResponseCode("99999", "操作失败")),
    /**
     * 用户端通用错误
     */
    SERVICE_ERROR(new ResponseCode("A9999", "用户端错误")),
    /**
     * 服务端通用错误
     */
    SERVER_ERROR(new ResponseCode("B9999", "服务端错误")),
    /**
     * 第三方服务通用错误
     */
    THIRD_PARTY_SERVICE_ERROR(new ResponseCode("C9999", "调用第三方服务出错"));

    private final ResponseCode response;

    public String getCode() {
        return response.getCode();
    }

    public String getMessage() {
        return response.getMessage();
    }
}

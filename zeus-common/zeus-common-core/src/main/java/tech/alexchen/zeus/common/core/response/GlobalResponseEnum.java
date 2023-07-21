package tech.alexchen.zeus.common.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum GlobalResponseEnum implements Responsive {

    SUCCESS(new ResponseCode("00000", "操作成功")),
    SERVICE_ERROR(new ResponseCode("A0001", "用户端错误")),
    SERVER_ERROR(new ResponseCode("B0001", "服务端错误")),
    THIRD_PARTY_SERVICE_ERROR(new ResponseCode("C0001", "调用第三方服务出错")),
    FAILED(new ResponseCode("99999", "操作失败"));

    private final ResponseCode response;

}

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
    FAILED(new ResponseCode("99999", "操作失败"));

    private final ResponseCode response;

    public String getCode() {
        return response.getCode();
    }

    public String getMessage() {
        return response.getMessage();
    }
}

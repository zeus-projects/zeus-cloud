package tech.alexchen.zeus.common.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author alexchen
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCode {

    /**
     * 响应状态码，00000 为正常，其他为错误
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    public static ResponseCode of(String code, String message) {
        return new ResponseCode(code, message);
    }

}

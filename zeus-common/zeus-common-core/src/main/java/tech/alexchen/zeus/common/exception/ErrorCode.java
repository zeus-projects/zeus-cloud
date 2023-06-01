package tech.alexchen.zeus.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 错误码对象
 *
 * @author alexchen
 */
@Data
@AllArgsConstructor
public class ErrorCode {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

}

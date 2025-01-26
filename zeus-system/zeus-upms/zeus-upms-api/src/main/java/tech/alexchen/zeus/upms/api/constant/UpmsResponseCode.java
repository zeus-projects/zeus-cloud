package tech.alexchen.zeus.upms.api.constant;

import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 * @since 2025-01-23 09:25
 */
public interface UpmsResponseCode {

    ResponseCode SYS_DEPT_PARENT_NOT_EXISTS = ResponseCode.of("10101", "父级部门不存在");
    ResponseCode SYS_DEPT_NAME_DUPLICATE = ResponseCode.of("10102", "部门名称重复");

}

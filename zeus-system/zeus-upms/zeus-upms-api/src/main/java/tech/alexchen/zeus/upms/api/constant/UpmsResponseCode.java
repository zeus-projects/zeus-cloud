package tech.alexchen.zeus.upms.api.constant;

import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 * @since 2025-01-23 09:25
 */
public interface UpmsResponseCode {

    ResponseCode SYS_DEPT_PARENT_NOT_EXISTS = ResponseCode.of("10101", "父级部门不存在");
    ResponseCode SYS_DEPT_NAME_DUPLICATE = ResponseCode.of("10102", "部门名称重复");

    ResponseCode SYS_MENU_PARENT_NOT_EXISTS = ResponseCode.of("10201", "父级菜单不存在");
    ResponseCode SYS_MENU_NAME_DUPLICATE = ResponseCode.of("10202", "菜单名称重复");
    ResponseCode SYS_MENU_HAS_SUBMENU = ResponseCode.of("10203", "菜单无法删除，请先删除子菜单");

    ResponseCode SYS_ROLE_NAME_DUPLICATE = ResponseCode.of("10301", "角色名称重复");

    ResponseCode SYS_USERNAME_DUPLICATE = ResponseCode.of("10401", "用户名称重复");
    ResponseCode SYS_USER_PHONE_DUPLICATE = ResponseCode.of("10402", "用户手机号码重复");
    ResponseCode SYS_USER_LOCKED = ResponseCode.of("10403", "用户已冻结");

}

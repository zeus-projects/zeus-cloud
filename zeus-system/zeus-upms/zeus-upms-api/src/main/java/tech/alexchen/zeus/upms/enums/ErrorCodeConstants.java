package tech.alexchen.zeus.upms.enums;

import tech.alexchen.zeus.common.core.exception.ErrorCode;

/**
 * @author alexchen
 */
public interface ErrorCodeConstants {

    // 部门模块
    ErrorCode DEPT_NAME_DUPLICATE = new ErrorCode("A00001", "已经存在该名字的部门");
    ErrorCode DEPT_PARENT_NOT_EXIST = new ErrorCode("A00001", "父级部门不存在");
    ErrorCode DEPT_NOT_FOUND = new ErrorCode("A00001", "当前部门不存在");
    ErrorCode DEPT_DISABLE = new ErrorCode("A00001", "部门状态不可用");
    ErrorCode DEPT_PARENT_IS_CHILD = new ErrorCode("A00001", "不能设置自己的子部门为父部门");
    ErrorCode DEPT_PARENT_ERROR = new ErrorCode("A00001", "不能设置自己为父部门");

    // 用户模块
    ErrorCode USER_USERNAME_EXISTS = new ErrorCode("A00001", "用户账号已经存在");
    ErrorCode USER_PHONE_EXISTS = new ErrorCode("A00001", "手机号已经存在");

}

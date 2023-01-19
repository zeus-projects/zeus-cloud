package tech.alexchen.zeus.upms.enums;

import tech.alexchen.zeus.starter.exception.ErrorCode;

/**
 * @author alexchen
 */
public interface ErrorCodeConstants {

    // 部门模块

    ErrorCode DEPT_NAME_DUPLICATE = new ErrorCode(1, "已经存在该名字的部门");
    ErrorCode DEPT_PARENT_NOT_EXIST = new ErrorCode(1, "父级部门不存在");
    ErrorCode DEPT_NOT_FOUND = new ErrorCode(1, "当前部门不存在");
    ErrorCode DEPT_DISABLE = new ErrorCode(1, "部门状态不可用");
    ErrorCode DEPT_PARENT_IS_CHILD = new ErrorCode(1, "不能设置自己的子部门为父部门");
    ErrorCode DEPT_PARENT_ERROR = new ErrorCode(1, "不能设置自己为父部门");


}

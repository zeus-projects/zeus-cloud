package tech.alexchen.zeus.upms.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.alexchen.zeus.common.core.response.ResponseCode;

/**
 * @author alexchen
 */

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    // 部门模块
    DEPT_NAME_DUPLICATE ( new ResponseCode("A00001", "已经存在该名字的部门")),
    DEPT_PARENT_NOT_EXIST ( new ResponseCode("A00001", "父级部门不存在")),
    DEPT_NOT_FOUND ( new ResponseCode("A00001", "当前部门不存在")),
    DEPT_DISABLE ( new ResponseCode("A00001", "部门状态不可用")),
    DEPT_PARENT_IS_CHILD ( new ResponseCode("A00001", "不能设置自己的子部门为父部门")),
    DEPT_PARENT_ERROR ( new ResponseCode("A00001", "不能设置自己为父部门")),

    
    USER_USERNAME_EXISTS ( new ResponseCode("A00001", "用户账号已经存在")),
    USER_PHONE_EXISTS ( new ResponseCode("A00001", "手机号已经存在"));

    private final ResponseCode response;

}

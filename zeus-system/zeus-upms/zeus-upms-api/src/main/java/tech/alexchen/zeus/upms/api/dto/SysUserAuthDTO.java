package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author alexchen
 */
@Data
public class SysUserAuthDTO implements Serializable {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String username;

    /**
     * 密码密文
     */
    @Schema(description = "密码密文")
    private String password;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 拓展字段:邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
     * 角色
     */
    @Schema(description = "角色")
    private Set<Long> roles;

    /**
     * 权限信息
     */
    @Schema(description = "权限信息")
    private Set<String> permissions;

    /**
     * 状态（0：正常 1：冻结）
     */
    @Schema(description = "状态（0：正常 1：冻结）", defaultValue = "0")
    private Integer status;
}

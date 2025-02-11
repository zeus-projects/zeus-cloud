package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import tech.alexchen.zeus.upms.api.bo.SysDeptRoleBO;

import java.io.Serializable;
import java.time.LocalDate;
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
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String fullname;

    /**
     * 拓展字段:昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Integer gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

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
     * 状态（0：正常 1：冻结）
     */
    @Schema(description = "状态（0：正常 1：冻结）", defaultValue = "0")
    private Integer status;

    /**
     * 部门角色列表
     */
    @NotEmpty(message = "部门角色列表不能为空")
    @Schema(description = "部门角色列表")
    private Set<SysDeptRoleBO> deptRoles;

    /**
     * 权限信息
     */
    @Schema(description = "权限信息")
    private Set<String> permissions;


}

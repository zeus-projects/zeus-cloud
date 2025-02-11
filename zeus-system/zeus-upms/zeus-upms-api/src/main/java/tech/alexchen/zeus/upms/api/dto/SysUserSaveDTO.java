package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.alexchen.zeus.upms.api.bo.SysDeptRoleBO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author alexchen
 */
@Data
@Schema
public class SysUserSaveDTO implements Serializable {

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名称")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String fullname;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
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
    @NotNull(message = "手机号码不能为空")
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 拓展字段:邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

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
    private List<SysDeptRoleBO> deptRoles;
}

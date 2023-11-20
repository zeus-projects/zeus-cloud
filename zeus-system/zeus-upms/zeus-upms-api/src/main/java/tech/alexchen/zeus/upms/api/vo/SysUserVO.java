package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author alexchen
 */
@Data
@Schema
public class SysUserVO implements Serializable {

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
    @Schema(description = "性别")
    private Integer gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private Long birthday;

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
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

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
     * 状态（0：正常 1：冻结）
     */
    @Schema(description = "状态（0：正常 1：冻结）", defaultValue = "0")
    private Integer status;

}

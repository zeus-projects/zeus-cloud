package tech.alexchen.zeus.upms.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.JdbcType;
import tech.alexchen.zeus.common.data.mybatis.entity.BaseTenantEntity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名称")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 拓展字段:昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 盐值
     */
    @Schema(description = "盐值")
    private String salt;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Integer sex;

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
    private List<String> role_ids;

    /**
     * 锁定标记：0(未锁定)、1(已锁定)
     */
    @Schema(description = "锁定标记：0(未锁定)、1(已锁定)")
    private String lockFlag;

    /**
     * 状态（0：正常 1：停用）
     */
    @Schema(description = "状态（0：正常 1：停用）")
    private Integer status;

    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Schema(name = "updateBy", description = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.VARCHAR)
    private String updateBy;

    /**
     * 最后更新时间
     */
    @Schema(name = "updateTime", description = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @Hidden
    @TableLogic
    private Integer deleted;
}

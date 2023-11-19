package tech.alexchen.zeus.upms.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.JdbcType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */

@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="sys_menu")
public class SysMenu implements Serializable {

    /**
     * 菜单id
     */
    @NotNull(message="[菜单id]不能为空")
    @Schema(name = "菜单id")
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message="[菜单名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @Schema(name = "菜单名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;

    /**
     * 父菜单ID
     */
    @NotNull(message="[父菜单ID]不能为空")
    @Schema(name = "父菜单ID")
    private Long parentId;

    /**
     * 菜单类型
     */
    @NotNull(message="[菜单类型]不能为空")
    @Schema(name = "菜单类型")
    private Integer type;

    /**
     * 显示顺序
     */
    @NotNull(message="[显示顺序]不能为空")
    @Schema(name = "显示顺序")
    private Integer sort;

    /**
     * 权限标识
     */
    @NotBlank(message="[权限标识]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @Schema(name = "权限标识")
    @Length(max= 100,message="编码长度不能超过100")
    private String permission;

    /**
     * 路由地址
     */
    @Size(max= 200,message="编码长度不能超过200")
    @Schema(name = "路由地址")
    @Length(max= 200,message="编码长度不能超过200")
    private String path;

    /**
     * 菜单图标
     */
    @Size(max= 100,message="编码长度不能超过100")
    @Schema(name = "菜单图标")
    @Length(max= 100,message="编码长度不能超过100")
    private String icon;

    /**
     * 组件路径
     */
    @Size(max= 255,message="编码长度不能超过255")
    @Schema(name = "组件路径")
    @Length(max= 255,message="编码长度不能超过255")
    private String component;

    /**
     * 状态（0：正常 1：停用）
     */
    @NotNull(message="[状态（0：正常 1：停用）]不能为空")
    @Schema(name = "状态（0：正常 1：停用）")
    private Integer status;

    /**
     * 是否可见
     */
    @NotNull(message="[是否可见]不能为空")
    @Schema(name = "是否可见")
    private Boolean visible;

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

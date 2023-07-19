package tech.alexchen.zeus.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import tech.alexchen.zeus.common.data.mybatis.entity.BaseDO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author alexchen
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_menu")
public class SysMenu extends BaseDO implements Serializable {

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

}

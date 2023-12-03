package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author alexchen
 */

@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuUpdateDTO implements Serializable {

    /**
     * 菜单id
     */
    @NotNull(message = "菜单id不能为空")
    @Schema(description = "菜单id")
    private Long id;

    /**
     * 菜单名称
     */
    @Length(max = 64, message = "编码长度不能超过64")
    @Schema(description = "菜单名称")
    private String name;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;

    /**
     * 菜单类型
     */
    @NotNull(message = "菜单类型不能为空")
    @Schema(description = "菜单类型")
    private Integer type;

    /**
     * 排序权重
     */
    @Schema(description = "排序权重")
    private Integer weight;

    /**
     * 权限标识
     */
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "权限标识")
    private String permission;

    /**
     * 路由地址
     */
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "路由地址")
    private String path;

    /**
     * 菜单图标
     */
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 是否隐藏（0:显示；1:隐藏）
     */
    @Schema(description = "是否隐藏（0:显示；1:隐藏）")
    private Integer hide;

    /**
     * 是否需要登陆才可访问（0:不需要；1:需要）
     */
    @Schema(description = "是否需要登陆才可访问（0:不需要；1:需要）")
    private Integer requiresAuth;

}

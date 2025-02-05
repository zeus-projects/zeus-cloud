package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 255)
    @Schema(description = "菜单名称", example = "系统管理")
    private String name;

    /**
     * 父菜单ID
     */
    @NotNull(message = "父菜单ID不能为空")
    @Schema(description = "父菜单ID", example = "0")
    private Long parentId;

    /**
     * 菜单类型
     */
    @NotNull(message = "菜单类型不能为空")
    @Max(value = 3)
    @Min(value = 0)
    @Schema(description = "菜单类型（0:目录；1:菜单；2:按钮；3:外链）", example = "0", maximum = "3", minimum = "0")
    private Integer type;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permission;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 是否隐藏（0:显示；1:隐藏）
     */
    @Max(value = 1)
    @Min(value = 0)
    @Schema(description = "是否隐藏（0:显示；1:隐藏）", defaultValue = "0")
    private Integer hide;

    /**
     * 排序权重
     */
    @Min(value = 0)
    @NotNull(message = "排序权重不能为空")
    @Schema(description = "排序", example = "1")
    private Integer sort;

}

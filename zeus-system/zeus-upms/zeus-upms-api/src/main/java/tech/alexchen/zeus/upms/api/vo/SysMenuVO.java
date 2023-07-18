package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class SysMenuVO implements Serializable {

    @Schema(name = "菜单 id")
    private Long id;

    @Schema(name = "菜单名称")
    private String name;

    @Schema(name = "父菜单ID")
    private Long parentId;

    @Schema(name = "菜单类型")
    private Integer type;

    @Schema(name = "显示顺序")
    private Integer sort;

    @Schema(name = "权限标识")
    private String permission;

    @Schema(name = "路由地址")
    private String path;

    @Schema(name = "菜单图标")
    private String icon;

    @Schema(name = "组件路径")
    private String component;

    @Schema(name = "状态（0：正常 1：停用）")
    private Integer status;

    @Schema(name = "是否可见")
    private Boolean visible;
}

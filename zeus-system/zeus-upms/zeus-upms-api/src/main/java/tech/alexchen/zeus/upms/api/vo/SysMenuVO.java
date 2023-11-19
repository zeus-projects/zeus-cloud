package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class SysMenuVO implements Serializable {

    @Schema(description = "菜单 id")
    private Long id;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单类型")
    private Integer type;

    @Schema(description = "显示顺序")
    private Integer weight;

    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏（0:显示；1:隐藏）")
    private Integer hide;

    @Schema(description = "是否需要登陆才可访问（0:不需要；1:需要）")
    private Integer requiresAuth;
}

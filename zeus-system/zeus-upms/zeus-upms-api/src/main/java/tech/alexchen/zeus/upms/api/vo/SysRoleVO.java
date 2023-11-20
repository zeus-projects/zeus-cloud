package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleVO {

    /**
     * 角色 id
     */
    @Schema(description = "角色 id")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 角色类型
     */
    @Schema(description = "角色类型", defaultValue = "1", example = "1")
    private Integer type;

    /**
     * 角色权限编码
     */
    @Schema(description = "角色权限编码")
    private String permission;

    /**
     * 数据权限类型（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）
     */
    @Schema(description = "数据权限类型（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）", defaultValue = "0", example = "0")
    private Integer dataScopeType;

    /**
     * 数据范围(指定部门数组)
     */
    @Schema(description = "数据范围(指定部门数组)")
    private Set<Long> dataScope;


    /**
     * 菜单权限
     */
    @Schema(description = "菜单权限")
    private Set<Long> menus;
}

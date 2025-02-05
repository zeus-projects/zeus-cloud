package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 角色权限编码
     */
    @Schema(description = "角色权限编码")
    private String permission;

    /**
     * 数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）
     */
    @Schema(description = "数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）")
    private Integer dataScope;

    /**
     * 状态（0：正常 1：停用）
     */
    @Schema(description = "状态（0：正常 1：停用）")
    private Integer status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

}

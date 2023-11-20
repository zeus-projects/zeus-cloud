package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUpdateDTO {

    /**
     * 角色 id
     */
    @NotNull(message = "角色 id不能为空")
    @Schema(description = "角色 id")
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 64, message = "长度不能超过64")
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    @Length(max = 255, message = "长度不能超过255")
    private String description;

    /**
     * 角色类型
     */
    @NotNull(message = "角色类型不能为空")
    @Schema(description = "角色类型", defaultValue = "1", example = "1")
    private Integer type;

    /**
     * 角色权限编码
     */
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "角色权限编码")
    private String permission;

    /**
     * 数据权限类型（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）
     */
    @NotNull(message = "数据权限类型不能为空")
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

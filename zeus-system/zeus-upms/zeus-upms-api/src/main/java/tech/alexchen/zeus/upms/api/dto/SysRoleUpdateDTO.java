package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    @Length(max = 255, message = "长度不能超过255")
    private String description;

    /**
     * 角色权限编码
     */
    @Length(max = 255, message = "长度不能超过255")
    @Schema(description = "角色权限编码")
    private String permission;

    /**
     * 数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）
     */
    @NotNull(message = "数据权限类型不能为空")
    @Schema(description = "数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）", defaultValue = "0")
    private Integer dataScope;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
}

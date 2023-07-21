package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;
import tech.alexchen.zeus.common.core.validation.UpdateGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
@Schema(name = "部门 DTO")
public class SysDeptDTO implements Serializable {

    @Schema(name = "id", description = "部门 id（更新时必需）", requiredMode = RequiredMode.NOT_REQUIRED, example = "1")
    @NotNull(message = "更新部门时，id 不能为空", groups = UpdateGroup.class)
    private Long id;

    @Schema(name = "name", description = "部门名称", requiredMode = RequiredMode.REQUIRED, example = "平台部")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @Schema(name = "parentId", description = "上级部门 ID", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "上级部门 id 不能为空")
    @Min(value = 1, message = "上级部门 id 不能小于 1")
    private Long parentId;

//    @Schema(name = "level", description = "部门等级", requiredMode = RequiredMode.NOT_REQUIRED, example = "2")
//    private Integer level;

    @Schema(name = "sort", description = "排序", requiredMode = RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(name = "status", description = "部门状态（0正常 1停用）", requiredMode = RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "部门状态不能为空")
    private Integer status;
}

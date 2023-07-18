package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.alexchen.zeus.common.core.validation.SaveGroup;
import tech.alexchen.zeus.common.core.validation.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class SysDeptDTO implements Serializable {

    @Schema(name = "部门名称", required = true, example = "销售部")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @Schema(name = "上级部门 ID", required = true, example = "1")
    @NotNull(message = "上级部门 id 不能为空")
    private Long parentId;

    @Schema(name = "部门等级", required = true, example = "1")
    @NotNull(message = "部门等级 不能为空", groups = { SaveGroup.class, UpdateGroup.class })
    private Integer level;

    @Schema(name = "排序", required = true, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(name = "部门状态", required = true, example = "0")
    @NotNull(message = "部门状态不能为空")
    private Integer status;

    @Schema(name = "租户 ID", required = true, example = "0")
    @NotNull(message = "租户 id 不能为空")
    private Long tenantId;
}

package tech.alexchen.zeus.upms.controller.admin.dept.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tech.alexchen.zeus.starter.enums.CommonStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class DeptBaseVO implements Serializable {

    @ApiModelProperty(value = "部门名称", required = true, example = "销售部")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(value = "上级部门 ID", required = true, example = "1")
    @NotNull(message = "上级部门 id 不能为空")
    private Long parentId;

    @ApiModelProperty(value = "部门等级", required = true, example = "1")
    @NotNull(message = "部门等级 不能为空")
    private Integer level;

    @ApiModelProperty(value = "排序", required = true, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "部门状态", required = true, example = "0")
    @NotNull(message = "部门状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "租户 ID", required = true, example = "0")
    @NotNull(message = "租户 id 不能为空")
    private Long tenantId;

}

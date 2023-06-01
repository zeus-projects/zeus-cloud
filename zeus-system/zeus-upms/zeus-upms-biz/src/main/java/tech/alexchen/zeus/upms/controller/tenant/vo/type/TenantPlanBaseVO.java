package tech.alexchen.zeus.upms.controller.tenant.vo.type;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author alexchen
 */
@Data
public class TenantPlanBaseVO implements Serializable {

    @ApiModelProperty(value = "租户套餐名称", required = true, example = "系统租户")
    @NotNull(message = "类型名称不能为空")
    private String name;

    @ApiModelProperty(value = "租户套餐状态", required = true, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "关联的菜单编号", required = true)
    @NotNull(message = "关联的菜单编号不能为空")
    private Set<Long> menuIds;

}

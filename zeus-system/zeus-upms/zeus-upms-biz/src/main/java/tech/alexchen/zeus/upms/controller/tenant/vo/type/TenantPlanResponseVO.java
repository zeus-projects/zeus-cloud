package tech.alexchen.zeus.upms.controller.tenant.vo.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 租户套餐 - ResponseVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantPlanResponseVO extends TenantPlanBaseVO {

    @ApiModelProperty(value = "租户套餐 ID", required = true, example = "1024")
    private Long id;

}

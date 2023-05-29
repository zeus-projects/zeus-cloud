package tech.alexchen.zeus.upms.controller.tenant.vo.type;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 租户套餐 - RequestVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantPlanRequestVO extends TenantPlanBaseVO {


}

package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */

@ApiModel("系统管理 - 租户 - ResponseVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantResponseVO extends TenantBaseVO {

    @ApiModelProperty(value = "租户 ID", required = true, example = "1024")
    private Long id;
}

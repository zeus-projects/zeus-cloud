package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 租户类型 - UpdateVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantTypeUpdateVO extends  TenantTypeBaseVO {

    @ApiModelProperty(value = "租户类型 ID", required = true, example = "1024")
    @NotNull(message = "id 不能为空")
    private Long id;

}

package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class TenantBaseVO implements Serializable {

    @ApiModelProperty(value = "租户名称", required = true, example = "超级管理员")
    @NotBlank(message = "租户名称不能为空")
    private String name;

    @ApiModelProperty(value = "租户类型 ID", required = true, example = "1")
    @NotNull(message = "租户类型 id 不能为空")
    private Integer tenantTypeId;

    @ApiModelProperty(value = "租户类型名称", required = true, example = "系统租户")
    @NotBlank(message = "租户类型名称不能为空")
    private String tenantTypeName;

    @ApiModelProperty(value = "租户状态", required = true, example = "0")
    @NotNull(message = "状态不能为空")
    private String status;

}

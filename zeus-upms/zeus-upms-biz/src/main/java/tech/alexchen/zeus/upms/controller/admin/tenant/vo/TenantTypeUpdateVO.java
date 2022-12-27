package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author alexchen
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantTypeUpdateVO extends  TenantTypeBaseVO {

    @NotNull(message = "id 不能为空")
    private Long id;

}

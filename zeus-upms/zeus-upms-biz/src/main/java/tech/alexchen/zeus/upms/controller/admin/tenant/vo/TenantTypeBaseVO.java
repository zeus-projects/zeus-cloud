package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author alexchen
 */
@Data
public class TenantTypeBaseVO implements Serializable {

    @NotNull(message = "套餐名不能为空")
    private String name;

    @NotNull(message = "状态不能为空")
    private Integer status;

    @NotNull(message = "关联的菜单编号不能为空")
    private Set<Long> menuIds;

}

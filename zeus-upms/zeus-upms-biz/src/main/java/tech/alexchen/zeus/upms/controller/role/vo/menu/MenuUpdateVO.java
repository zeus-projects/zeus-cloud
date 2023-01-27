package tech.alexchen.zeus.upms.controller.role.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 菜单 - UpdateVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MenuUpdateVO extends MenuBaseVO {

    @NotNull(message="[菜单id]不能为空")
    @ApiModelProperty("菜单id")
    private Long id;
}

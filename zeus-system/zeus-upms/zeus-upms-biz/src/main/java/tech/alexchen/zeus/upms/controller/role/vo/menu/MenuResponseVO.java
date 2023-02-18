package tech.alexchen.zeus.upms.controller.role.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 菜单 - ResponseVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MenuResponseVO extends MenuBaseVO {

    @ApiModelProperty("菜单id")
    private Long id;

}

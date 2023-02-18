package tech.alexchen.zeus.upms.controller.role.vo.menu;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 菜单 - RequestVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MenuRequestVO extends MenuBaseVO {

}

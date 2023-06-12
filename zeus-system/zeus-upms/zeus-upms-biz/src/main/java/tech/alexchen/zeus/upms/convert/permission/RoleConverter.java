package tech.alexchen.zeus.upms.convert.permission;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleResponseVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.entity.permission.RoleDO;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleConverter extends BaseConverter<RoleDO, RoleSaveVO, RoleUpdateVO, RoleResponseVO> {

}

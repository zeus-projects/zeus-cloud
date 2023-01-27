package tech.alexchen.zeus.upms.convert.permission;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleResponseVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConvert;
import tech.alexchen.zeus.upms.domain.permission.RoleDO;

/**
 * @author alexchen
 */
@Mapper
public interface RoleConvert extends BaseConvert<RoleDO, RoleSaveVO, RoleUpdateVO, RoleResponseVO> {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);
}

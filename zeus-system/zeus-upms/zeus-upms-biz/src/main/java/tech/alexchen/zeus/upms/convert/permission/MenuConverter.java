package tech.alexchen.zeus.upms.convert.permission;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuResponseVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.domain.permission.MenuDO;

/**
 * @author alexchen
 */
@Mapper
public interface MenuConverter extends BaseConverter<MenuDO, MenuSaveVO, MenuUpdateVO, MenuResponseVO> {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

}

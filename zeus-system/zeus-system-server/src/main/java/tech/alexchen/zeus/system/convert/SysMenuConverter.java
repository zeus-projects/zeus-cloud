package tech.alexchen.zeus.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.system.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.system.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.system.api.vo.SysMenuVO;
import tech.alexchen.zeus.system.entity.SysMenu;

import java.util.List;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysMenuConverter {

    SysMenu toEntity(SysMenuSaveDTO dto);

    SysMenu toEntity(SysMenuUpdateDTO dto);

    List<SysMenuVO> toSysMenuVOList(List<SysMenu> list);

    SysMenuVO toVO(SysMenu menu);
}

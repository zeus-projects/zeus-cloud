package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysMenu;
import tech.alexchen.zeus.upms.api.vo.SysMenuVO;

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

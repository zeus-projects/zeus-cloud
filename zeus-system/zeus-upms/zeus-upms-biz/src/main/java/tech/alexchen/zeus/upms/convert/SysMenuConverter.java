package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysMenuDTO;
import tech.alexchen.zeus.upms.api.vo.SysMenuVO;
import tech.alexchen.zeus.upms.api.entity.SysMenu;

import java.util.List;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysMenuConverter {

    SysMenu toEntity(SysMenuDTO dto);

    List<SysMenuVO> toSysMenuVOList(List<SysMenu> list);
}

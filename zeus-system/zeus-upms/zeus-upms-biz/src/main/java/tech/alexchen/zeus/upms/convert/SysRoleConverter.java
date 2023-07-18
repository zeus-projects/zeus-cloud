package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysRoleDTO;
import tech.alexchen.zeus.upms.entity.SysRole;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysRoleConverter {

    SysRole toEntity(SysRoleDTO dto);
}

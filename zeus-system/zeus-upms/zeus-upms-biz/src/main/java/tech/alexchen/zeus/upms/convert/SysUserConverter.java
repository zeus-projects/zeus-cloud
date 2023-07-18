package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysUserDTO;
import tech.alexchen.zeus.upms.entity.SysUser;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysUserConverter {

    SysUser toEntity(SysUserDTO dto);
}
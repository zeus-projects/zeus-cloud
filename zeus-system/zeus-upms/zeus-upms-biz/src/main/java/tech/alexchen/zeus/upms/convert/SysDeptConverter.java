package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysDeptDTO;
import tech.alexchen.zeus.upms.api.entity.SysDept;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysDeptConverter {

    SysDept toEntity(SysDeptDTO dto);
}

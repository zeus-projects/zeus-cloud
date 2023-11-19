package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysDept;
import tech.alexchen.zeus.upms.api.vo.SysDeptVO;

import java.util.List;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysDeptConverter {

    SysDept toEntity(SysDeptSaveDTO dto);

    SysDept toEntity(SysDeptUpdateDTO dto);

    SysDeptVO toVO(SysDept entity);

    List<SysDeptVO> toVOList(List<SysDept> deptList);
}

package tech.alexchen.zeus.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.system.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.system.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.system.api.vo.SysRoleVO;
import tech.alexchen.zeus.system.entity.SysRole;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysRoleConverter {

    SysRole toEntity(SysRoleSaveDTO dto);


    SysRole toEntity(SysRoleUpdateDTO dto);

    SysRoleVO toVO(SysRole entity);

    PageResult<SysRoleVO> toPageVO(PageResult<SysRole> pageResult);
}

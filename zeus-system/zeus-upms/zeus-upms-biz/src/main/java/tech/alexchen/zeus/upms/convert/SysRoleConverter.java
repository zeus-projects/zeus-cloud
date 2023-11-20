package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysRole;
import tech.alexchen.zeus.upms.api.vo.SysRoleVO;

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

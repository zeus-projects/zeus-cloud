package tech.alexchen.zeus.upms.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysUserVO;
import tech.alexchen.zeus.upms.entity.SysUser;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {cn.hutool.core.date.LocalDateTimeUtil.class})
public interface SysUserConverter {


    @Mapping(target = "birthday", expression = "java(LocalDateTimeUtil.of(dto.getBirthday()).toLocalDate())")
    SysUser toEntity(SysUserSaveDTO dto);

    @Mapping(target = "birthday", expression = "java(LocalDateTimeUtil.of(dto.getBirthday()).toLocalDate())")
    SysUser toEntity(SysUserUpdateDTO dto);

    @Mapping(target = "birthday", expression = "java(LocalDateTimeUtil.toEpochMilli(entity.getBirthday()))")
    SysUserVO toVO(SysUser entity);

    SysUserAuthDTO toAuthDTO(SysUser entity);

    PageResult<SysUserVO> toPageVO(PageResult<SysUser> pageResult);

}
package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysRole;
import tech.alexchen.zeus.upms.convert.SysRoleConverter;
import tech.alexchen.zeus.upms.mapper.SysRoleMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;

import java.util.Set;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper mapper;
    private final SysRoleConverter converter;

    @Override
    public Long saveRole(SysRoleSaveDTO dto) {
        SysRole role = mapper.selectByName(dto.getName());
        Assert.isNull(role, "Role name {} duplicate", dto.getName());
        SysRole entity = converter.toEntity(dto);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateRoleById(SysRoleUpdateDTO dto) {
        SysRole entity = converter.toEntity(dto);
        mapper.updateById(entity);
    }

    @Override
    public void removeRoleById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public PageResult<SysRole> getDeptPage(PageParam page) {
        return mapper.selectPage(page, null);
    }

    @Override
    public void updateRoleMenus(Long roleId, Set<Long> menus) {
        SysRole role = mapper.selectById(roleId);
        role.setMenus(menus);
        mapper.updateById(role);
    }

}

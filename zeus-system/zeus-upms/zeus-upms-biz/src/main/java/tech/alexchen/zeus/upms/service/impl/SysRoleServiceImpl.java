package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.constant.UpmsResponseCode;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysRoleConverter;
import tech.alexchen.zeus.upms.entity.SysMenu;
import tech.alexchen.zeus.upms.entity.SysRole;
import tech.alexchen.zeus.upms.entity.SysRoleMenu;
import tech.alexchen.zeus.upms.mapper.SysMenuMapper;
import tech.alexchen.zeus.upms.mapper.SysRoleMapper;
import tech.alexchen.zeus.upms.mapper.SysRoleMenuMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper roleMapper;
    private final SysRoleMenuMapper roleMenuMapper;
    private final SysMenuMapper menuMapper;
    private final SysRoleConverter converter;

    @Override
    public Long saveRole(SysRoleSaveDTO dto) {
        if (roleMapper.isRoleNameExists(dto.getName(), null)) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_ROLE_NAME_DUPLICATE);
        }
        SysRole entity = converter.toEntity(dto);
        roleMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateRoleById(SysRoleUpdateDTO dto) {
        if (roleMapper.isRoleNameExists(dto.getName(), dto.getId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_ROLE_NAME_DUPLICATE);
        }
        SysRole entity = converter.toEntity(dto);
        roleMapper.updateById(entity);
    }

    @Override
    public void removeRoleById(Long id) {
        // TODO 删除角色时，删除关联信息
        roleMapper.deleteById(id);
    }

    @Override
    public SysRole getById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<SysRole> getRolePage(PageParam page) {
        return roleMapper.selectPage(page, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenus(Long roleId, Set<Long> menus) {
        roleMenuMapper.deleteByRoleId(roleId);

        List<SysRoleMenu> entities = menus.stream().map(id -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(id);
            return sysRoleMenu;
        }).collect(Collectors.toList());
        roleMenuMapper.insert(entities);
    }

    @Override
    public Set<String> getRolePermissions(Set<Long> roleIdSet) {
        Set<String> permissions = new HashSet<>();
        if (CollUtil.isEmpty(roleIdSet)) {
            return permissions;
        }
        // 获取角色的权限标识
        List<SysRole> roles = roleMapper.selectList(Wrappers.<SysRole>lambdaQuery()
                .in(SysRole::getId, roleIdSet));
        for (SysRole role : roles) {
            permissions.add(role.getPermission());
        }
        // 获取菜单的权限标识
        List<SysRoleMenu> roleMenus = roleMenuMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery()
                .in(SysRoleMenu::getRoleId, roleIdSet));
        Set<Long> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toSet());
        if (CollUtil.isNotEmpty(menuIds)) {
            List<SysMenu> menus = menuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, menuIds));
            for (SysMenu menu : menus) {
                permissions.add(menu.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public Set<Long> getRoleMenus(Long roleId) {
        return roleMenuMapper.getRoleMenuIds(roleId);
    }

}

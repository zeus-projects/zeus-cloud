package tech.alexchen.zeus.upms.service;

import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.entity.SysRole;

import java.util.Set;

/**
 * @author alexchen
 */
public interface SysRoleService {

    /**
     * 保存角色
     *
     * @param dto 角色信息
     */
    Long saveRole(SysRoleSaveDTO dto);

    /**
     * 更新角色
     *
     * @param dto 角色信息
     */
    void updateRoleById(SysRoleUpdateDTO dto);

    /**
     * 删除角色
     *
     * @param id 角色 id
     */
    void removeRoleById(Long id);

    /**
     * 分页查询
     */
    PageResult<SysRole> getRolePage(PageParam page);

    /**
     * 更新角色的菜单权限
     *
     * @param roleId 角色 id
     * @param menus  菜单 id 列表
     */
    void updateRoleMenus(Long roleId, Set<Long> menus);

    /**
     * 获取角色菜单权限列表
     */
    Set<String> getRolePermissions(Set<Long> roleIdSet);
}


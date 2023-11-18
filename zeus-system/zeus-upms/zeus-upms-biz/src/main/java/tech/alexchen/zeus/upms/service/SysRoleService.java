package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.api.entity.SysRole;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 保存角色
     *
     * @param entity 角色信息
     */
    void saveRole(SysRole entity);

    /**
     * 更新角色
     * @param entity 角色信息
     */
    Boolean updateRoleById(SysRole entity);

    /**
     * 删除角色
     * @param id 角色 id
     */
    Boolean removeRoleById(Long id);


    /**
     * 更新角色的菜单权限
     * @param roleId 角色 id
     * @param menus 菜单 id 列表
     */
    Boolean updateRoleMenus(Long roleId, List<Long> menus);
}

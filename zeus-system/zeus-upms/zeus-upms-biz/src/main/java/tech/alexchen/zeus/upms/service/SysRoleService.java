package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.entity.SysRole;

/**
 * @author alexchen
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 保存角色
     * @param entity 角色信息
     * @return 角色 id
     */
    Long saveRole(SysRole entity);

    /**
     * 更新角色
     * @param entity 角色信息
     */
    void updateRole(SysRole entity);

    /**
     * 删除角色
     * @param id 角色 id
     */
    void removeRoleById(Long id);

}

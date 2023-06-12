package tech.alexchen.zeus.upms.service.permission;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleUpdateVO;
import tech.alexchen.zeus.upms.entity.permission.RoleDO;

/**
 * @author alexchen
 */
public interface RoleService extends IService<RoleDO> {

    /**
     * 保存角色
     * @param vo 角色信息
     * @return 角色 id
     */
    Long saveRole(RoleSaveVO vo);

    /**
     * 更新角色
     * @param vo 角色信息
     */
    void updateRole(RoleUpdateVO vo);

    /**
     * 删除角色
     * @param id 角色 id
     */
    void removeRoleById(Long id);

}

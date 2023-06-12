package tech.alexchen.zeus.upms.service.permission;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleUpdateVO;
import tech.alexchen.zeus.upms.entity.permission.RoleDO;
import tech.alexchen.zeus.upms.mapper.permission.RoleMapper;

/**
 * @author alexchen
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    @Override
    public Long saveRole(RoleSaveVO vo) {
        return null;
    }

    @Override
    public void updateRole(RoleUpdateVO vo) {

    }

    @Override
    public void removeRoleById(Long id) {

    }
}

package tech.alexchen.zeus.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.entity.SysRole;
import tech.alexchen.zeus.upms.mapper.SysRoleMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;

/**
 * @author alexchen
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Long saveRole(SysRole entity) {
        return null;
    }

    @Override
    public void updateRole(SysRole entity) {

    }

    @Override
    public void removeRoleById(Long id) {

    }
}

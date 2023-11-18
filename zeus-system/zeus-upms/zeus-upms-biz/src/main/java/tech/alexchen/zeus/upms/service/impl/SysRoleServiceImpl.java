package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.api.entity.SysRole;
import tech.alexchen.zeus.upms.mapper.SysRoleMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;

import java.util.List;

/**
 * @author alexchen
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public void saveRole(SysRole entity) {
        SysRole role = this.getOne(Wrappers.<SysRole>lambdaQuery()
                .eq(SysRole::getName, entity.getName()));
        Assert.isNull(role, "Role name {} duplicate", entity.getName());
        this.save(entity);
    }

    @Override
    public Boolean updateRoleById(SysRole entity) {
       return this.updateById(entity);
    }

    @Override
    public Boolean removeRoleById(Long id) {
        return this.removeById(id);
    }

    @Override
    public Boolean updateRoleMenus(Long roleId, List<Long> menus) {
        SysRole role = this.getById(roleId);
        role.setMenus(CollUtil.join(menus, StringPool.COMMA));
        return this.updateById(role);
    }
}

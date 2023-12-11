package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysRole;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alexchen
 */
public interface SysRoleMapper extends BaseMapperX<SysRole> {

    default SysRole selectByName(String name) {
        return selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getName, name));
    }

    default Set<String> getRolePermissions(Set<Long> roleIdSet) {
        List<SysRole> roles = selectList(Wrappers.<SysRole>lambdaQuery()
                .select(SysRole::getId, SysRole::getPermission)
                .in(SysRole::getId, roleIdSet));

        return roles.stream().map(SysRole::getPermission).collect(Collectors.toSet());
    }
}

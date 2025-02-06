package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysRoleMenu;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alexchen
 */
public interface SysRoleMenuMapper extends BaseMapperX<SysRoleMenu> {

    default void deleteByRoleId(Long roleId) {
        delete(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, roleId));
    }

    default Set<Long> getRoleMenuIds(Long roleId) {
        List<SysRoleMenu> roleMenus = selectList(Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getRoleId, roleId));
        return roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toSet());
    }

}

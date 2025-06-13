package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import tech.alexchen.zeus.upms.entity.SysMenu;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    default boolean isParentMenuNotExists(Long parentId) {
        Long count = selectCount(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getId, parentId));
        return count == 0;
    }

    default boolean isDeptNameExists(String name, Long parentId, @Nullable Long id) {
        Long count = selectCount(Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getName, name)
                .eq(SysMenu::getParentId, parentId)
                .ne(id != null, SysMenu::getId, id)
        );
        return count > 0;
    }

    default boolean canMenuDeleted(Long id) {
        return selectCount(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, id)) == 0;
    }

    /**
     * 获取父级菜单下的各级子菜单列表
     */
    List<SysMenu> selectMenusByParentId(@Param("parentId") Long parentId);
}

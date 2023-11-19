package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.upms.api.entity.SysMenu;

import java.util.List;


/**
 * @author alexchen
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    default List<SysMenu> selectMenusByParentId(Long parentId) {
        return this.selectList(Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getParentId, parentId));
    }

    default SysMenu selectByName(String name) {
        return this.selectOne(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getName, name));
    }
}

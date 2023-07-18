package tech.alexchen.zeus.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.entity.SysMenu;
import tech.alexchen.zeus.upms.mapper.SysMenuMapper;
import tech.alexchen.zeus.upms.service.SysMenuService;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuConverter converter;
    
    @Override
    public Long saveMenu(SysMenu entity) {
        // 检查父菜单
        checkParentMenu(entity.getParentId());
        // 检查名称是否重复
        checkMenuName(entity.getParentId(), entity.getName());
        // 插入数据库
        this.save(entity);
        return entity.getId();
    }

    @Override
    public void updateMenu(SysMenu entity) {
        // 检查父菜单
        checkParentMenu(entity.getParentId(), entity.getId());
        // 检查名称是否重复
        checkMenuName(entity.getParentId(), entity.getName(), entity.getId());
        this.updateById(entity);
    }

    @Override
    public void removeMenuById(Long id) {
        // 检查是否有子菜单
        this.removeById(id);
    }


    private void checkParentMenu(Long parentId, Long id) {

    }

    private void checkParentMenu(Long parentId) {
        checkParentMenu(parentId, null);
    }

    private void checkMenuName(Long parentId, String name, Long id) {

    }

    private void checkMenuName(Long parentId, String name) {
        checkMenuName(parentId, name,null);
    }
}

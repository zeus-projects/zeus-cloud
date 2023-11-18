package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.api.entity.SysMenu;
import tech.alexchen.zeus.upms.mapper.SysMenuMapper;
import tech.alexchen.zeus.upms.service.SysMenuService;

import java.util.List;

/**
 * @author alexchen
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public void saveMenu(SysMenu entity) {
        checkParentMenuExists(entity.getParentId());
        checkDuplicateMenuInfo(entity.getName());
        this.save(entity);
    }

    @Override
    public Boolean updateMenu(SysMenu entity) {
        checkParentMenuExists(entity.getParentId());
        return this.updateById(entity);
    }

    @Override
    public Boolean removeMenuById(Long id) {
        List<SysMenu> menus = this.list(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, id));
        Assert.isTrue(CollUtil.isEmpty(menus), "Cannot delete a menu with submenus");
        return this.removeById(id);
    }

    private void checkParentMenuExists(Long parentId) {
        SysMenu parentMenu = this.getById(parentId);
        Assert.notNull(parentMenu, "No Menu with id {} exists", parentId);
    }

    private void checkDuplicateMenuInfo(String name) {
        SysMenu menu = this.getOne(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getName, name));
        Assert.isNull(menu, "Menu name {} duplicate", name);
    }
}

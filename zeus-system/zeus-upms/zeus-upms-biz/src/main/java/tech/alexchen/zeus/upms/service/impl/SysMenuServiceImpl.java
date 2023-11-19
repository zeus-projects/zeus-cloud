package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.api.entity.SysMenu;
import tech.alexchen.zeus.upms.mapper.SysMenuMapper;
import tech.alexchen.zeus.upms.service.SysMenuService;

import java.util.List;
import java.util.Objects;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper mapper;
    private final SysMenuConverter converter;

    private static final Long ROOT_MENU_ID = 0L;

    @Override
    public Long saveMenu(SysMenuSaveDTO dto) {
        checkParentMenuExists(dto.getParentId());
        checkDuplicateMenuInfo(dto.getName());
        SysMenu entity = converter.toEntity(dto);
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateMenu(SysMenuUpdateDTO dto) {
        checkParentMenuExists(dto.getParentId());
        SysMenu entity = converter.toEntity(dto);
        mapper.updateById(entity);
    }

    @Override
    public void removeMenuById(Long id) {
        List<SysMenu> menus = mapper.selectMenusByParentId(id);
        Assert.isTrue(CollUtil.isEmpty(menus), "Cannot delete a menu with submenus");
        mapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getMenuList() {
        return mapper.selectList(null);
    }

    /**
     * 检查父级菜单是否存在
     */
    private void checkParentMenuExists(Long parentId) {
        if (ROOT_MENU_ID.equals(parentId)) {
            return;
        }
        SysMenu parentMenu = mapper.selectById(parentId);
        Assert.notNull(parentMenu, "No Menu with id {} exists", parentId);
    }

    /**
     * 检查名称是否重复
     */
    private void checkDuplicateMenuInfo(String name) {
        SysMenu menu = mapper.selectByName(name);
        Assert.isNull(menu, "Menu name {} duplicate", name);
    }
}

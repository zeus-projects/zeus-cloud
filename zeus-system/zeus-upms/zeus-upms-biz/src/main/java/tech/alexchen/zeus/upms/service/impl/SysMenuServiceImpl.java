package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.api.constant.UpmsResponseCode;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.entity.SysMenu;
import tech.alexchen.zeus.upms.mapper.SysMenuMapper;
import tech.alexchen.zeus.upms.service.SysMenuService;

import java.util.List;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;
    private final SysMenuConverter sysMenuConverter;

    @Override
    public Long saveMenu(SysMenuSaveDTO dto) {
        if (!SysConstant.ROOT_MENU_ID.equals(dto.getParentId()) && sysMenuMapper.isParentMenuNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_MENU_PARENT_NOT_EXISTS);
        }
        // 检查同级别下是否存在相同名称的菜单
        if (sysMenuMapper.isDeptNameExists(dto.getName(), dto.getParentId(), null)) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_MENU_NAME_DUPLICATE);
        }
        SysMenu entity = sysMenuConverter.toEntity(dto);
        sysMenuMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateMenu(SysMenuUpdateDTO dto) {
        if (!SysConstant.ROOT_MENU_ID.equals(dto.getParentId()) && sysMenuMapper.isParentMenuNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_MENU_PARENT_NOT_EXISTS);
        }
        // 检查同级别下是否存在相同名称的菜单
        if (sysMenuMapper.isDeptNameExists(dto.getName(), dto.getParentId(), dto.getId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_MENU_NAME_DUPLICATE);
        }
        SysMenu entity = sysMenuConverter.toEntity(dto);
        sysMenuMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeMenuById(Long id) {
        if (!sysMenuMapper.canMenuDeleted(id)) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_MENU_HAS_SUBMENU);
        }
        // TODO 从角色和用户关联信息中，删除该菜单
        sysMenuMapper.deleteById(id);
    }

    @Override
    public SysMenu getMenuById(Long id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public List<Tree<Long>> getMenuTree(Long parentId) {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenusByParentId(parentId);
        // 构建树型结构
        return TreeUtil.build(sysMenus, parentId, (menu, tree) -> {
            tree.setId(menu.getId());
            tree.setName(menu.getName());
            tree.setParentId(menu.getParentId());
            tree.setWeight(menu.getSort());
            tree.putExtra("sort", menu.getSort());
            tree.putExtra("type", menu.getType());
            tree.putExtra("permission", menu.getPermission());
            tree.putExtra("path", menu.getPath());
            tree.putExtra("icon", menu.getIcon());
            tree.putExtra("component", menu.getComponent());
        });
    }

}

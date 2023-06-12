package tech.alexchen.zeus.upms.service.permission;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuUpdateVO;
import tech.alexchen.zeus.upms.convert.permission.MenuConverter;
import tech.alexchen.zeus.upms.entity.permission.MenuDO;
import tech.alexchen.zeus.upms.mapper.permission.MenuMapper;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {

    private final MenuConverter converter;
    
    @Override
    public Long saveMenu(MenuSaveVO vo) {
        MenuDO menu = converter.convertFromSave(vo);
        // 检查父菜单
        checkParentMenu(vo.getParentId());
        // 检查名称是否重复
        checkMenuName(vo.getParentId(), vo.getName());
        // 插入数据库
        this.save(menu);
        return menu.getId();
    }

    @Override
    public void updateMenu(MenuUpdateVO vo) {
        MenuDO menu = converter.convertFromUpdate(vo);
        // 检查父菜单
        checkParentMenu(vo.getParentId(), vo.getId());
        // 检查名称是否重复
        checkMenuName(vo.getParentId(), vo.getName(), vo.getId());
        // 更新数据库
        this.updateById(menu);
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

package tech.alexchen.zeus.upms.service.permission;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuUpdateVO;
import tech.alexchen.zeus.upms.entity.permission.MenuDO;

/**
 * @author alexchen
 */
public interface MenuService extends IService<MenuDO> {

    /**
     * 保存菜单
     * @param vo 菜单信息
     * @return 菜单 id
     */
    Long saveMenu(MenuSaveVO vo);

    /**
     * 更新菜单
     * @param vo 菜单信息
     */
    void updateMenu(MenuUpdateVO vo);

    /**
     * 删除菜单
     * @param id 菜单 id
     */
    void removeMenuById(Long id);
}

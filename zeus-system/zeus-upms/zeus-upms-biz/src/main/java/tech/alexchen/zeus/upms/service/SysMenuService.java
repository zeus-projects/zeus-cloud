package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.entity.SysMenu;

/**
 * @author alexchen
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 保存菜单
     * @param entity 菜单信息
     * @return 菜单 id
     */
    Long saveMenu(SysMenu entity);

    /**
     * 更新菜单
     * @param entity 菜单信息
     */
    void updateMenu(SysMenu entity);

    /**
     * 删除菜单
     * @param id 菜单 id
     */
    void removeMenuById(Long id);
}

package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.api.entity.SysMenu;

/**
 * @author alexchen
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 保存菜单
     *
     * @param entity 菜单信息
     */
    void saveMenu(SysMenu entity);

    /**
     * 更新菜单
     *
     * @param entity 菜单信息
     */
    Boolean updateMenu(SysMenu entity);

    /**
     * 删除菜单
     *
     * @param id 菜单 id
     */
    Boolean removeMenuById(Long id);
}

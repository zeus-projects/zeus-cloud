package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysMenu;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alexchen
 */
public interface SysMenuService {

    /**
     * 保存菜单
     *
     * @param dto 菜单信息
     */
    Long saveMenu(@Valid SysMenuSaveDTO dto);

    /**
     * 更新菜单
     *
     * @param dto 菜单信息
     */
    void updateMenu(@Valid SysMenuUpdateDTO dto);

    /**
     * 删除菜单
     *
     * @param id 菜单 id
     */
    void removeMenuById(Long id);

    /**
     * 获取菜单列表
     */
    List<SysMenu> getMenuList();
}

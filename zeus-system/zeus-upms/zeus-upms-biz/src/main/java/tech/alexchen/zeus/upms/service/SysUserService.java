package tech.alexchen.zeus.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.api.entity.SysUser;

/**
 * @author alexchen
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 添加用户
     *
     * @param entity 用户信息
     * @return 用户 id
     */
    Long saveUser(SysUser entity);

    /**
     * 修改用户
     *
     * @param entity 用户信息
     */
    Boolean updateUser(SysUser entity);

    /**
     * @param id 用户 id
     */
    Boolean removeUserById(Long id);
}

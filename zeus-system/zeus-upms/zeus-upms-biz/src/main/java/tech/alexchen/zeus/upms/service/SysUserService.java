package tech.alexchen.zeus.upms.service;

import jakarta.validation.Valid;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.entity.SysUser;


/**
 * @author alexchen
 */
public interface SysUserService {

    /**
     * 添加用户
     *
     * @param dto 用户信息
     * @return 用户 id
     */
    Long saveUser(@Valid SysUserSaveDTO dto);

    /**
     * 修改用户
     *
     * @param dto 用户信息
     */
    void updateUser(@Valid SysUserUpdateDTO dto);

    /**
     * @param id 用户 id
     */
    void removeUserById(Long id);

    /**
     * 根据用户 id 查询用户信息
     */
    SysUser getUserById(Long id);

    /**
     * 根据用户名查询
     */
    SysUser getUserByName(String username);

    /**
     * 查询用户授权信息
     */
    SysUserAuthDTO getUserAuthInfo(String username);

    /**
     * 分页查询用户信息
     */
    PageResult<SysUser> getUserPage(PageParam param);

    /**
     * 查询当前登录用户的信息
     */
    SysUserAuthDTO getCurrentUserInfo();

}

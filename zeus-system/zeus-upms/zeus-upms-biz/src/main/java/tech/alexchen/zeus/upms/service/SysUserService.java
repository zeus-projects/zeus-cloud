package tech.alexchen.zeus.upms.service;

import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysUser;

import javax.validation.Valid;

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
     * 分页查询用户信息
     */
    PageResult<SysUser> getUserPage(PageParam param);
}

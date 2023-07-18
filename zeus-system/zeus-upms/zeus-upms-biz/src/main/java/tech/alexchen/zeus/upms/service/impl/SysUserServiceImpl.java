package tech.alexchen.zeus.upms.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.core.exception.ExceptionUtil;
import tech.alexchen.zeus.upms.entity.SysUser;
import tech.alexchen.zeus.upms.mapper.SysUserMapper;
import tech.alexchen.zeus.upms.service.SysUserService;

import static tech.alexchen.zeus.upms.api.enums.ErrorCodeConstants.USER_PHONE_EXISTS;
import static tech.alexchen.zeus.upms.api.enums.ErrorCodeConstants.USER_USERNAME_EXISTS;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Long saveUser(SysUser entity) {
        checkUser(entity);
        this.save(entity);
        return entity.getId();
    }

    private void checkUser(SysUser entity) {

    }

    @Override
    public void updateUser(SysUser entity) {
        checkUser(entity);
        this.updateById(entity);
    }

    @Override
    public void removeUserById(Long id) {
        // TODO
        this.removeById(id);
    }

    /**
     * 检查用户名是否重复
     *
     * @param id       用户id
     * @param username 用户名
     */
    private void checkUsernameUnique(@Nullable Long id, String username) {
        // 更新时可能为空？
        if (StrUtil.isBlank(username)) {
            return;
        }
        SysUser user = this.getBaseMapper().selectByUsername(username);
        if (user == null) {
            return;
        }
        if (id == null || !user.getId().equals(id)) {
            throw ExceptionUtil.exception(USER_USERNAME_EXISTS);
        }
    }

    /**
     * 检查用户手机号码是否重复
     *
     * @param id    用户 id
     * @param phone 手机号码
     */
    private void checkPhoneUnique(@Nullable Long id, String phone) {
        if (StrUtil.isBlank(phone)) {
            return;
        }
        SysUser user = this.getBaseMapper().selectByPhone(phone);
        if (id == null || !user.getPhone().equals(phone)) {
            throw ExceptionUtil.exception(USER_PHONE_EXISTS);
        }
    }

}

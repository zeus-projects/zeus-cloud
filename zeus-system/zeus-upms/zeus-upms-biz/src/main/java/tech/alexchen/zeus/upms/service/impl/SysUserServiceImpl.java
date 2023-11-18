package tech.alexchen.zeus.upms.service.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.api.entity.SysUser;
import tech.alexchen.zeus.upms.mapper.SysUserMapper;
import tech.alexchen.zeus.upms.service.SysUserService;

/**
 * @author alexchen
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Long saveUser(SysUser entity) {
        checkDuplicateUserInfo(entity.getUsername(), entity.getPhone());
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateUser(SysUser entity) {
        return this.updateById(entity);
    }

    @Override
    public Boolean removeUserById(Long id) {
        return this.removeById(id);
    }

    private void checkDuplicateUserInfo(String username, String phone) {
        SysUser user = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        Assert.isNull(user, "User with username {} is exist", username);

        SysUser user1 = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhone, phone));
        Assert.isNull(user1, "User with phone {} is exist", phone);
    }

}

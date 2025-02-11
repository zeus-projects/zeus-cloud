package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysUser;

/**
 * @author alexchen
 */
public interface SysUserMapper extends BaseMapperX<SysUser> {

    default SysUser selectByUsername(String username) {
        return this.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    }

    default boolean isUsernameExists(String username) {
        Long count = selectCount(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        return count > 0;
    }

    default boolean isUserPhoneExists(String phone) {
        Long count = selectCount(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhone, phone));
        return count > 0;
    }
}

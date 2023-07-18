package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.upms.entity.SysUser;

/**
 * @author alexchen
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    default SysUser selectByUsername(String username) {
        return this.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, username));
    }

    /**
     * 根据用户手机号码查询用户信息
     * @param phone 手机号码
     * @return 用户信息
     */
    default SysUser selectByPhone(String phone) {
        return this.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getPhone, phone));
    }

    /**
     * 根据用户邮箱查询用户信息
     * @param email 邮箱
     * @return 用户信息
     */
    default SysUser selectByEmail(String email) {
        return this.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getEmail, email));
    }
}

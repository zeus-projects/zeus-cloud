package tech.alexchen.zeus.upms.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.upms.entity.user.UserDO;

/**
 * @author alexchen
 */
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    default UserDO selectByUsername(String username) {
        return this.selectOne(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUsername, username));
    }

    /**
     * 根据用户手机号码查询用户信息
     * @param phone 手机号码
     * @return 用户信息
     */
    default UserDO selectByPhone(String phone) {
        return this.selectOne(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getPhone, phone));
    }

    /**
     * 根据用户邮箱查询用户信息
     * @param email 邮箱
     * @return 用户信息
     */
    default UserDO selectByEmail(String email) {
        return this.selectOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getEmail, email));
    }
}

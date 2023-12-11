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
}

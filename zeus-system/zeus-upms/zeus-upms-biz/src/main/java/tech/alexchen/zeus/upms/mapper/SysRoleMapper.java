package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.api.entity.SysRole;

/**
 * @author alexchen
 */
public interface SysRoleMapper extends BaseMapperX<SysRole> {

    default SysRole selectByName(String name) {
        return selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getName, name));
    }
}

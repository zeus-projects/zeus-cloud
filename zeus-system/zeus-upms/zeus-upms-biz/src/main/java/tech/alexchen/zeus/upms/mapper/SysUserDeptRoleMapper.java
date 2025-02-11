package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysUserDeptRole;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysUserDeptRoleMapper extends BaseMapperX<SysUserDeptRole> {

    default void deleteByUserId(Long userId) {
        delete(Wrappers.<SysUserDeptRole>lambdaQuery().eq(SysUserDeptRole::getUserId, userId));
    }

    default List<SysUserDeptRole> selectListByUserId(Long userId) {
        return selectList(Wrappers.<SysUserDeptRole>lambdaQuery().eq(SysUserDeptRole::getUserId, userId));
    }
}

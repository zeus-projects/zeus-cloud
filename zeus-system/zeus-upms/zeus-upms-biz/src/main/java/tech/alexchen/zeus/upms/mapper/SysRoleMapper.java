package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.lang.Nullable;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysRole;

/**
 * @author alexchen
 */
public interface SysRoleMapper extends BaseMapperX<SysRole> {

    default boolean isRoleNameExists(String name, @Nullable Long id) {
        Long count = selectCount(Wrappers.<SysRole>lambdaQuery()
                .eq(SysRole::getName, name)
                .ne(id != null, SysRole::getId, id)
        );
        return count > 0;
    }

}

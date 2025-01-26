package tech.alexchen.zeus.upms.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.upms.entity.SysDept;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysDeptMapper extends BaseMapperX<SysDept> {

    /**
     * 检查父级部门是否不存在
     *
     * @param parentId 部门id
     * @return true/false 不存在/存在
     */
    default boolean isParentDeptNotExists(Long parentId) {
        Long count = selectCount(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getId, parentId));
        return count == 0;
    }

    /**
     * 检查部门名称是否已经存在；如果指定部门 id，则查询除了该部门之外是否存在其他相等的名称
     *
     * @param deptName 部门名称
     * @param id 部门id
     * @return true/false 存在/不存在
     */
    default boolean isDeptNameExists(String deptName, @Nullable Long id) {
        Long count = selectCount(Wrappers.<SysDept>lambdaQuery()
                .eq(SysDept::getName, deptName)
                .ne(id != null, SysDept::getId, id)
        );
        return count > 0;
    }

    /**
     * 查询父部门下的子部门列表，如果 parentId 为空，或者为 0，则不带条件查询，即查询全部
     *
     * @param parentId 父部门 ID
     * @return 部门列表数据
     */
    List<SysDept> selectDeptListByParentId(@Param("parentId") Long parentId);
}

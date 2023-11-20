package tech.alexchen.zeus.upms.mapper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.alexchen.zeus.common.data.mybatis.enhance.BaseMapperX;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.api.entity.SysDept;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysDeptMapper extends BaseMapperX<SysDept> {

    /**
     * 根据名称查询部门
     *
     * @param name 部门名称
     */
    default SysDept selectByName(String name) {
        return selectOne(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getName, name));
    }

    /**
     * 分页条件查询部门信息
     *
     * @param page   分页信息
     * @param id 部门 ID
     * @param name 部门名称
     * @return 部门分页数据
     */
    default PageResult<SysDept> selectDeptPage(PageParam page, Long id, String name) {
        return this.selectPage(page, Wrappers.<SysDept>lambdaQuery()
                .eq(ObjectUtil.isNotNull(id), SysDept::getId, id)
                .eq(StrUtil.isNotBlank(name), SysDept::getName, name)
        );
    }

    /**
     * 查询父部门下的子部门列表，如果 parentId 为空，或者为 0，则不带条件查询，即查询全部
     *
     * @param parentId 父部门 ID
     * @return 部门列表数据
     */
    default List<SysDept> selectDeptListByParentId(Long parentId) {
        return this.selectList(Wrappers.<SysDept>lambdaQuery()
                .eq(null != parentId && !SysConstant.ROOT_DEPT_ID.equals(parentId),
                        SysDept::getParentId, parentId));
    }
}

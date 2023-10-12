package tech.alexchen.zeus.upms.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.entity.SysDept;

/**
 * @author alexchen
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 根据父部门 id 和部门的名称查询一条记录
     * @param parentId 父部门 id
     * @param name 部门名称
     * @return 部门信息
     */
    default SysDept selectByParentIdAndName(Long parentId, String name) {
        return selectOne(Wrappers.<SysDept>lambdaQuery()
                .eq(SysDept::getParentId, parentId)
                .eq(SysDept::getName, name));
    }

    /**
     * 分页条件查询部门信息
     * @param page 分页信息
     * @param entity 过滤条件
     * @return 部门分页数据
     */
    default Page<SysDept> page(Page<SysDept> page, SysDept entity) {
        return this.selectPage(page, Wrappers.<SysDept>lambdaQuery()
                .eq(StrUtil.isNotBlank(entity.getName()), SysDept::getName, entity.getName())
        );
    }

}

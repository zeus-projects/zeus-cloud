package tech.alexchen.zeus.upms.mapper.dept;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptRequestVO;
import tech.alexchen.zeus.upms.entity.dept.DeptDO;

/**
 * @author alexchen
 */
public interface DeptMapper extends BaseMapper<DeptDO> {

    /**
     * 根据父部门 id 和部门的名称查询一条记录
     * @param parentId 父部门 id
     * @param name 部门名称
     * @return 部门信息
     */
    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(Wrappers.<DeptDO>lambdaQuery()
                .eq(DeptDO::getParentId, parentId)
                .eq(DeptDO::getName, name));
    }

    /**
     * 分页条件查询部门信息
     * @param page 分页信息
     * @param vo 过滤条件
     * @return 部门分页数据
     */
    default Page<DeptDO> page(Page page, DeptRequestVO vo) {
        return this.selectPage(page, Wrappers.<DeptDO>lambdaQuery()
                .eq(StrUtil.isNotBlank(vo.getName()), DeptDO::getName, vo.getName())
                .eq(vo.getStatus() != null, DeptDO::getStatus, vo.getStatus())
        );
    }

}

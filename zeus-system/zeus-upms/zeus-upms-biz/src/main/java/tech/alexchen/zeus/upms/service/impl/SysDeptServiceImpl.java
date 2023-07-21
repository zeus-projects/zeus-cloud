package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.core.constants.StatusConstant;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.entity.SysDept;
import tech.alexchen.zeus.upms.mapper.SysDeptMapper;
import tech.alexchen.zeus.upms.service.SysDeptService;

import java.util.List;

/**
 * @author alexchen
 *
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    
    @Override
    public Long saveDept(SysDept entity) {
        // 检查是否已经存在
        long count = this.count(Wrappers.<SysDept>lambdaQuery()
                .eq(SysDept::getName, entity.getName()));
        Assert.isTrue(count == 0, "部门 {} 已经存在", entity.getName());
        // 检查父部门；设置部门等级
        checkParentDept(entity);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean updateById(SysDept entity) {
        checkParentDept(entity);
        return super.updateById(entity);
    }

    private void checkParentDept(SysDept entity) {
        // parentId 表示是一级部门，设置其父部门为 ROOT_DEPT_ID，等级为 FIRST_DEPT_LEVEL
        if (entity.getParentId() == null) {
            entity.setParentId(SysConstant.ROOT_DEPT_ID);
            entity.setLevel(SysConstant.FIRST_DEPT_LEVEL);
        } else if (SysConstant.ROOT_DEPT_ID.equals(entity.getParentId())){
            entity.setLevel(SysConstant.FIRST_DEPT_LEVEL);
        } else {
            SysDept parentDept = this.getOne(Wrappers.<SysDept>lambdaQuery()
                    .eq(SysDept::getId, entity.getParentId())
                    .eq(SysDept::getStatus, StatusConstant.ENABLE));
            Assert.notNull(parentDept, "父部门 id {} 不存在", entity.getParentId());
            // 设置部门级别
            entity.setLevel(parentDept.getLevel() + 1);
        }
    }

    @Override
    public Boolean removeDept(Long id) {
        // TODO 检查部门下是否有用户
        return this.removeById(id);
    }

    @Override
    public Page<SysDept> pageDept(Page<SysDept> page, SysDept entity) {
        return this.getBaseMapper().page(page, entity);
    }

    @Override
    public List<Tree<Long>> getDeptTreeByParentId(Long parentId) {
        List<SysDept> deptList;
        // parentId 为 0，查询全部
        if (SysConstant.ROOT_DEPT_ID.equals(parentId)) {
            deptList = this.list();
        } else {
            // 查询 parentId 下面的子部门
            SysDept parentDept = getById(parentId);
            Assert.notNull(parentDept, "parentId {} 不存在", parentId);
            deptList = list(Wrappers.<SysDept>lambdaQuery()
                    .eq(SysDept::getParentId, parentId)
                    .gt(SysDept::getLevel, parentDept.getLevel())
            );
        }
        // 构建树型结构
        return TreeUtil.build(deptList, 0L, (dept, tree) -> {
            tree.setId(dept.getId());
            tree.setName(dept.getName());
            tree.setParentId(dept.getParentId());
            tree.setWeight(dept.getSort());
        });
    }

}

























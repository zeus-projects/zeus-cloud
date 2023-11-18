package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.api.entity.SysDept;
import tech.alexchen.zeus.upms.mapper.SysDeptMapper;
import tech.alexchen.zeus.upms.service.SysDeptService;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author alexchen
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public void saveDept(SysDept entity) {
        long count = this.count(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getName, entity.getName()));
        Assert.isTrue(count == 0, "Department {} already exists", entity.getName());
        checkParentIdExists(entity.getParentId());
        this.save(entity);
    }

    @Override
    public Boolean updateDept(SysDept entity) {
        checkParentIdExists(entity.getParentId());
        return updateById(entity);
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
        if (null == parentId || SysConstant.ROOT_DEPT_ID.equals(parentId)) {
            deptList = this.list();
        } else {
            checkParentIdExists(parentId);
            deptList = this.list(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getParentId, parentId));
        }
        // 构建树型结构
        return TreeUtil.build(deptList, 0L, (dept, tree) -> {
            tree.setId(dept.getId());
            tree.setName(dept.getName());
            tree.setParentId(dept.getParentId());
            tree.setWeight(dept.getWeight());
            tree.putExtra("createTime", dept.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        });
    }

    /**
     * 检测是否存在 id 为 parentId 的部门
     *
     * @param parentId 父级部门 id
     */
    private void checkParentIdExists(Long parentId) {
        Assert.notNull(parentId, "checkParentIdExist failed, parentId is null ");
        SysDept parentDept = this.getById(parentId);
        Assert.notNull(parentDept, "No department with id {} exists", parentId);
    }
}


























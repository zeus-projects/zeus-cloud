package tech.alexchen.zeus.upms.service.dept;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.enums.CommonStatusEnum;
import tech.alexchen.zeus.common.exception.ExceptionUtil;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptRequestVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.dept.DeptConverter;
import tech.alexchen.zeus.upms.domain.dept.DeptDO;
import tech.alexchen.zeus.upms.mapper.dept.DeptMapper;
import tech.alexchen.zeus.upms.enums.DeptIdEnum;
import tech.alexchen.zeus.upms.enums.ErrorCodeConstants;

import java.util.List;

/**
 * @author alexchen
 *
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptDO> implements DeptService {

    @Override
    public Long saveDept(DeptSaveVO vo) {
        // 如果 parentId 是 null，表示是最高一级的部门；由于 id 从 1 开始自增，使其 parentId 等于 0 表示是最高一级的部门
        if (vo.getParentId() == null) {
            vo.setParentId(DeptIdEnum.ROOT_DEPT_ID.getId());
            vo.setLevel(1);
        }
        // 检查父部门是否可用
        checkParentDeptEnable(vo.getParentId());
        // 检查名称是否重复
        checkDeptNameUnique(vo.getName(), vo.getParentId());
        DeptDO dept = DeptConverter.INSTANCE.convertFromSave(vo);
        this.save(dept);
        return dept.getId();
    }

    @Override
    public Boolean updateDept(DeptUpdateVO vo) {
        // 检查父部门是否可用
        checkParentDeptEnable(vo.getId(), vo.getParentId(), vo.getLevel());
        // 检查名称是否重复
        checkDeptNameUnique(vo.getId(), vo.getName(), vo.getParentId());
        DeptDO dept = DeptConverter.INSTANCE.convertFromUpdate(vo);
        return this.updateById(dept);
    }

    @Override
    public Boolean removeDept(Long id) {
        // 检查部门是否存在
        checkDeptExists(id);
        // 检查部门下是否有用户
        checkDeptUser(id);
        return this.removeById(id);
    }

    @Override
    public Page<DeptDO> pageDept(Page page, DeptRequestVO vo) {
        Page<DeptDO> pageRes = this.getBaseMapper().page(page, vo);
        return pageRes;
    }

    @Override
    public List<Tree<Long>> getDeptTreeByParentId(Long parentId) {
        List<DeptDO> deptList;
        // parentId 为 0，查询全部
        if (DeptIdEnum.ROOT_DEPT_ID.getId().equals(parentId)) {
            deptList = list();
        } else {
            // 查询 parentId 下面的子部门
            DeptDO parentDept = getById(parentId);
            if (parentDept == null) {
                throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_PARENT_NOT_EXIST);
            }
            deptList = list(Wrappers.<DeptDO>lambdaQuery()
                    .eq(DeptDO::getParentId, parentId)
                    .gt(DeptDO::getLevel, parentDept.getLevel())
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

    /**
     * 检查部门是否存在
     * @param id 部门 id
     */
    private void checkDeptExists(Long id) {
        if (id == null) {
            return;
        }
        DeptDO dept = this.getById(id);
        if (dept == null) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_NOT_FOUND);
        }
    }

    /**
     * 检验父部门是否有效
     *
     * @param id 部门 id
     * @param parentId 父部门 id
     */
    private void checkParentDeptEnable(Long id, Long parentId, Integer level) {
        if (parentId == null || DeptIdEnum.ROOT_DEPT_ID.getId().equals(parentId)) {
            return;
        }
        // 父部门不能为空
        DeptDO parentDept = getById(parentId);
        if (parentDept == null) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_PARENT_NOT_EXIST);
        }
        // 判断父部门是否可用
        if (!CommonStatusEnum.ENABLE.getStatus().equals(parentDept.getStatus())) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_DISABLE);
        }
        // 不能设置自己为父部门
        if (id != null && parentDept.getId().equals(id)) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_PARENT_ERROR);
        }
        // 父部门不能是原来的子部门
        if (level != null && parentDept.getLevel() < level) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_PARENT_IS_CHILD);
        }
    }

    /**
     * 用于插入时判断父部门 id 是否有效
     * @param parentId 父部门 id
     */
    private void checkParentDeptEnable(Long parentId) {
        checkParentDeptEnable(null, parentId, null);
    }

        /**
         * 检查部门名称是否重复
         * @param id 部门 id
         * @param deptName 部门名称
         * @param parentId 父部门 id
         */
    private void checkDeptNameUnique(Long id, String deptName, Long parentId) {
        DeptDO dept = getBaseMapper().selectByParentIdAndName(parentId, deptName);
        if (dept == null) {
            return;
        }
        // id 为 null，用于插入时判断，此时存在记录则名称重复
        if (id == null) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_NAME_DUPLICATE);
        }
        // id 不为 null，用于更新时判断，查出的记录不等于当前记录，则名称重复
        if (!dept.getId().equals(id)) {
            throw ExceptionUtil.exception(ErrorCodeConstants.DEPT_NAME_DUPLICATE);
        }
    }

    /**
     * 用于插入时判断名称重复
     * @param deptName 部门名称
     * @param parentId 父部门 id
     */
    private void checkDeptNameUnique(String deptName, Long parentId) {
        checkDeptNameUnique(null, deptName, parentId);
    }

    /**
     * 检查部门下是否有用户
     * @param id 部门 id
     */
    private void checkDeptUser(Long id) {
        // TODO
    }

}

























package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.api.constant.UpmsResponseCode;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysDeptConverter;
import tech.alexchen.zeus.upms.entity.SysDept;
import tech.alexchen.zeus.upms.mapper.SysDeptMapper;
import tech.alexchen.zeus.upms.service.SysDeptService;

import java.util.List;

/**
 * @author alexchen
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;

    private final SysDeptConverter sysDeptConverter;

    @Override
    public Long save(SysDeptSaveDTO dto) {
        if (!SysConstant.ROOT_DEPT_ID.equals(dto.getParentId()) && sysDeptMapper.isParentDeptNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_PARENT_NOT_EXISTS);
        }
        if (sysDeptMapper.isDeptNameExists(dto.getName(), null)) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_NAME_DUPLICATE);
        }
        SysDept sysDept = sysDeptConverter.toEntity(dto);
        sysDeptMapper.insert(sysDept);
        return sysDept.getId();
    }

    @Override
    public void updateById(SysDeptUpdateDTO dto) {
        if (!SysConstant.ROOT_DEPT_ID.equals(dto.getParentId()) && sysDeptMapper.isParentDeptNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_PARENT_NOT_EXISTS);
        }
        if (sysDeptMapper.isDeptNameExists(dto.getName(), dto.getId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_NAME_DUPLICATE);
        }
        SysDept sysDept = sysDeptConverter.toEntity(dto);
        sysDeptMapper.updateById(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        // TODO 删除关联的部门信息，或者在部门有关联数据时禁止删除
        sysDeptMapper.deleteById(id);
    }

    @Override
    public SysDept getById(Long id) {
        return sysDeptMapper.selectById(id);
    }

    @Override
    public List<SysDept> getListByParentId(Long parentId) {
        // 默认为 0，查询全部
        if (parentId == null) {
            parentId = SysConstant.ROOT_DEPT_ID;
        }
        return sysDeptMapper.selectDeptListByParentId(parentId);
    }

    @Override
    public List<Tree<Long>> getTreeByParentId(Long parentId) {
        // 默认为 0，查询全部
        if (parentId == null) {
            parentId = SysConstant.ROOT_DEPT_ID;
        }
        List<SysDept> deptList = this.getListByParentId(parentId);
        // 构建树型结构
        return TreeUtil.build(deptList, parentId, (dept, tree) -> {
            tree.setId(dept.getId());
            tree.setName(dept.getName());
            tree.setParentId(dept.getParentId());
            tree.setWeight(dept.getSort());
            tree.putExtra("sort", dept.getSort());
        });
    }

}

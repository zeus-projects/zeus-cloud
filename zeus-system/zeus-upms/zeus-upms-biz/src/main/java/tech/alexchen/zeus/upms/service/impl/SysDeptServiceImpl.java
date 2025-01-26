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

    private final SysDeptMapper mapper;
    private final SysDeptConverter converter;

    @Override
    public Long saveDept(SysDeptSaveDTO dto) {
        if (!SysConstant.ROOT_DEPT_ID.equals(dto.getParentId()) && mapper.isParentDeptNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_PARENT_NOT_EXISTS);
        }
        if (mapper.isDeptNameExists(dto.getName(), null)) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_NAME_DUPLICATE);
        }
        SysDept sysDept = converter.toEntity(dto);
        mapper.insert(sysDept);
        return sysDept.getId();
    }

    @Override
    public void updateDept(SysDeptUpdateDTO dto) {
        if (!SysConstant.ROOT_DEPT_ID.equals(dto.getParentId()) && mapper.isParentDeptNotExists(dto.getParentId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_PARENT_NOT_EXISTS);
        }
        if (mapper.isDeptNameExists(dto.getName(), dto.getId())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_DEPT_NAME_DUPLICATE);
        }
        SysDept sysDept = converter.toEntity(dto);
        mapper.updateById(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDept(Long id) {
        // TODO 删除关联的部门信息，或者在部门有关联数据时禁止删除
        mapper.deleteById(id);
    }

    @Override
    public SysDept getDeptById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SysDept> getDeptListByParentId(Long parentId) {
        return mapper.selectDeptListByParentId(parentId);
    }

    @Override
    public List<Tree<Long>> getDeptTreeByParentId(Long parentId) {
        List<SysDept> deptList = mapper.selectDeptListByParentId(parentId);
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


























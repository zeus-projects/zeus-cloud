package tech.alexchen.zeus.upms.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysDeptQueryDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysDeptConverter;
import tech.alexchen.zeus.upms.entity.SysDept;
import tech.alexchen.zeus.upms.mapper.SysDeptMapper;
import tech.alexchen.zeus.upms.service.SysDeptService;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static tech.alexchen.zeus.common.core.constants.CommonConstant.CREATE_TIME;
import static tech.alexchen.zeus.common.core.constants.CommonConstant.DATE_TIME_PATTERN;

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
        // 名称验重
        SysDept dept = mapper.selectByName(dto.getName());
        Assert.isNull(dept, "Department {} already exists", dto.getName());
        // 检查 parentId 是否有效
        checkParentIdExists(dto.getParentId());
        // 入库
        SysDept sysDept = converter.toEntity(dto);
        mapper.insert(sysDept);
        return sysDept.getId();
    }

    @Override
    public void updateDept(SysDeptUpdateDTO dto) {
        checkParentIdExists(dto.getParentId());
        SysDept sysDept = converter.toEntity(dto);
        mapper.updateById(sysDept);
    }

    @Override
    public void removeDept(Long id) {
        // TODO 检查部门下是否有用户
        mapper.deleteById(id);
    }

    @Override
    public SysDept getDeptById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public PageResult<SysDept> getDeptPage(PageParam page, SysDeptQueryDTO dto) {
        return mapper.selectDeptPage(page, dto.getId(), dto.getName());
    }

    @Override
    public List<SysDept> getDeptListByParentId(Long parentId) {
        return mapper.selectDeptListByParentId(parentId);
    }

    @Override
    public List<Tree<Long>> getDeptTreeByParentId(Long parentId) {
        List<SysDept> deptList = mapper.selectDeptListByParentId(parentId);
        // 构建树型结构
        return TreeUtil.build(deptList, 0L, (dept, tree) -> {
            tree.setId(dept.getId());
            tree.setName(dept.getName());
            tree.setParentId(dept.getParentId());
            tree.setWeight(dept.getSort());
            tree.putExtra(CREATE_TIME, dept.getCreateTime().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        });
    }

    /**
     * 检测是否存在 id 为 parentId 的部门
     *
     * @param parentId 父级部门 id
     */
    private void checkParentIdExists(Long parentId) {
        Assert.notNull(parentId, "checkParentIdExist failed, parentId is null ");
        SysDept parentDept = mapper.selectById(parentId);
        Assert.notNull(parentDept, "No department with id {} exists", parentId);
    }
}


























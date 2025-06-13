package tech.alexchen.zeus.upms.service;

import cn.hutool.core.lang.tree.Tree;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.entity.SysDept;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysDeptService {

    /**
     * 创建部门
     *
     * @param dto 部门信息
     * @return 部门 id
     */
    Long save(@Valid SysDeptSaveDTO dto);

    /**
     * 更新部门
     *
     * @param dto 部门信息
     */
    void updateById(@Valid SysDeptUpdateDTO dto);

    /**
     * 删除部门
     *
     * @param id 部门 id
     */
    void removeById(@NotNull Long id);

    /**
     * 根据 id 查询部门
     *
     * @param id 部门 id
     * @return SysDept
     */
    SysDept getById(@NotNull Long id);

    /**
     * 返回部门列表
     *
     * @param parentId 父级部门 ID
     */
    List<SysDept> getListByParentId(Long parentId);

    /**
     * 获取部门下的子部门
     *
     * @param parentId 父部门 id
     * @return 树型列表
     */
    List<Tree<Long>> getTreeByParentId(Long parentId);
}

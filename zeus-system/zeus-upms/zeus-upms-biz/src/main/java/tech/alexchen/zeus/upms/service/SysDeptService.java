package tech.alexchen.zeus.upms.service;

import cn.hutool.core.lang.tree.Tree;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysDeptQueryDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysDept;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    Long saveDept(@Valid SysDeptSaveDTO dto);

    /**
     * 更新部门
     *
     * @param dto 部门信息
     */
    void updateDept(@Valid SysDeptUpdateDTO dto);

    /**
     * 删除部门
     *
     * @param id 部门 id
     */
    void removeDept(@NotNull Long id);

    /**
     * 根据 id 查询部门
     *
     * @param id 部门 id
     * @return SysDept
     */
    SysDept getDeptById(@NotNull Long id);

    /**
     * 分页查询部门
     *
     * @param page 分页信息
     * @param dto  查询条件
     * @return 分页数据
     */
    PageResult<SysDept> getDeptPage(PageParam page, SysDeptQueryDTO dto);

    /**
     * 返回部门列表
     *
     * @param parentId 父级部门 ID
     */
    List<SysDept> getDeptListByParentId(Long parentId);

    /**
     * 获取部门下的子部门
     *
     * @param parentId 父部门 id
     * @return 树型列表
     */
    List<Tree<Long>> getDeptTreeByParentId(Long parentId);
}

package tech.alexchen.zeus.upms.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.entity.SysDept;

import java.util.List;

/**
 * @author alexchen
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 创建部门，处理受影响的其他数据
     * @param entity  部门信息
     * @return 部门 id
     */
    Long saveDept(SysDept entity);

//    /**
//     * 更新部门
//     * @param entity 部门信息
//     * @return 更新成功与否
//     */
//    Boolean updateDept(SysDept entity);

    /**
     * 删除部门
     * @param id 部门 id
     * @return 删除成功与否
     */
    Boolean removeDept(Long id);

    /**
     * 分页查询部门
     * @param page 分页信息
     * @param entity 查询条件
     * @return 分页数据
     */
    Page<SysDept> pageDept(Page<SysDept> page, SysDept entity);

    /**
     * 获取部门下的子部门
     *
     * @param parentId 父部门 id
     * @return 树型列表
     */
    List<Tree<Long>> getDeptTreeByParentId(Long parentId);

}

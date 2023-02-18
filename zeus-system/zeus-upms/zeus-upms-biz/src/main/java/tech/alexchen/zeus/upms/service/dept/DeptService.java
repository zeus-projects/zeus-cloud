package tech.alexchen.zeus.upms.service.dept;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptRequestVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.domain.dept.DeptDO;

import java.util.List;

/**
 * @author alexchen
 */
public interface DeptService extends IService<DeptDO> {

    /**
     * 创建部门，处理受影响的其他数据
     * @param saveVO  部门信息
     * @return 部门 id
     */
    Long saveDept(DeptSaveVO saveVO);

    /**
     * 更新部门
     * @param updateVO 部门信息
     * @return 更新成功与否
     */
    Boolean updateDept(DeptUpdateVO updateVO);

    /**
     * 删除部门
     * @param id 部门 id
     * @return 删除成功与否
     */
    Boolean removeDept(Long id);

    /**
     * 分页查询部门
     * @param page 分页信息
     * @param vo 查询条件
     * @return 分页 DO 数据
     */
    Page<DeptDO> pageDept(Page page, DeptRequestVO vo);

    /**
     * 获取部门下的子部门
     *
     * @param parentId 父部门 id
     * @return 树型列表
     * @throws Exception
     */
    List<Tree<Long>> getDeptTreeByParentId(Long parentId);

}

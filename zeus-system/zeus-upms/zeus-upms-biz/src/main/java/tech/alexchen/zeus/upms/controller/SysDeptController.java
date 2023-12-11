package tech.alexchen.zeus.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysDeptVO;
import tech.alexchen.zeus.upms.convert.SysDeptConverter;
import tech.alexchen.zeus.upms.entity.SysDept;
import tech.alexchen.zeus.upms.service.SysDeptService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 部门管理
 *
 * @author alexchen
 */
@Tag(name = "部门管理")
@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService sysDeptService;
    private final SysDeptConverter converter;

    /**
     * 创建部门
     */
    @PreAuthorize("pms.hasPermission('sys_dept.write')")
    @PostMapping
    @Operation(summary = "创建部门", description = "创建部门接口，创建成功后返回部门 id")
    public R<Long> save(@Valid @RequestBody SysDeptSaveDTO dto) {
        return R.ok(sysDeptService.saveDept(dto));
    }

    /**
     * 更新部门
     */
    @PreAuthorize("pms.hasPermission('sys_dept.write')")
    @PutMapping
    @Operation(summary = "更新部门", description = "更新部门信息")
    public R<Boolean> update(@Valid @RequestBody SysDeptUpdateDTO dto) {
        sysDeptService.updateDept(dto);
        return R.ok(true);
    }

    /**
     * 删除部门
     */
    @PreAuthorize("pms.hasPermission('sys_dept.write')")
    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门", parameters = {
            @Parameter(description = "部门 ID", example = "1")
    })
    public R<Boolean> removeById(@PathVariable @NotNull(message = "部门 id 不能为空") Long id) {
        sysDeptService.removeDept(id);
        return R.ok(true);
    }

    /**
     * 查询部门详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询部门详情", parameters = {
            @Parameter(description = "部门 ID", example = "1")
    })
    public R<SysDeptVO> getById(@PathVariable @NotNull(message = "部门 id 不能为空") Long id) {
        SysDept dept = sysDeptService.getDeptById(id);
        return R.ok(converter.toVO(dept));
    }

    /**
     * 查询部门列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询部门列表", parameters = {
            @Parameter(description = "父级部门 id", example = "0")
    })
    public R<List<SysDeptVO>> list(Long parentId) {
        List<SysDeptVO> voList = converter.toVOList(sysDeptService.getDeptListByParentId(parentId));
        return R.ok(voList);
    }

    /**
     * 查询部门列表树
     */
    @GetMapping("/tree")
    @Operation(summary = "查询部门列表树", parameters = {
            @Parameter(description = "父级部门 id", example = "0")
    })
    public R<List<Tree<Long>>> tree(Long parentId) {
        List<Tree<Long>> tree = sysDeptService.getDeptTreeByParentId(parentId);
        return R.ok(tree);
    }

}

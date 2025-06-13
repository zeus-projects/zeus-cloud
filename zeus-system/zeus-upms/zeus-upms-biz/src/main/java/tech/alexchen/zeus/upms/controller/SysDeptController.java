package tech.alexchen.zeus.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysDeptSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysDeptUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysDeptVO;
import tech.alexchen.zeus.upms.convert.SysDeptConverter;
import tech.alexchen.zeus.upms.entity.SysDept;
import tech.alexchen.zeus.upms.service.SysDeptService;

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
     * 新增部门
     */
    @PostMapping
    @Operation(summary = "新增部门", description = "新增部门接口，新增成功后返回部门 id")
    public R<Long> save(@Valid @RequestBody SysDeptSaveDTO dto) {
        return R.ok(sysDeptService.save(dto));
    }

    /**
     * 更新部门
     */
    @PutMapping
    @Operation(summary = "更新部门", description = "更新部门信息")
    public R<Boolean> update(@Valid @RequestBody SysDeptUpdateDTO dto) {
        sysDeptService.updateById(dto);
        return R.ok(true);
    }

    /**
     * 删除部门
     */
    @DeleteMapping
    @Operation(summary = "删除部门", description = "根据部门 id 删除部门", parameters = {
            @Parameter(description = "部门 ID", example = "1")
    })
    public R<Boolean> removeById(@RequestParam("id") @NotNull(message = "部门 id 不能为空") Long id) {
        sysDeptService.removeById(id);
        return R.ok(true);
    }

    /**
     * 查询部门详情
     */
    @GetMapping
    @Operation(summary = "查询部门详情", description = "根据部门 id 查询部门详情", parameters = {
            @Parameter(description = "部门 ID", example = "1")
    })
    public R<SysDeptVO> getById(@RequestParam("id") @NotNull(message = "部门 id 不能为空") Long id) {
        SysDept dept = sysDeptService.getById(id);
        return R.ok(converter.toVO(dept));
    }

    /**
     * 查询部门列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询部门列表", description = "查询指定部门及其各级子部门列表", parameters = {
            @Parameter(description = "父级部门 id", example = "0")
    })
    public R<List<SysDeptVO>> list(@RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        List<SysDeptVO> voList = converter.toVOList(sysDeptService.getListByParentId(parentId));
        return R.ok(voList);
    }

    /**
     * 查询部门列表树
     */
    @GetMapping("/tree")
    @Operation(summary = "查询部门列表树", description = "查询部门指定父节点开始的部门树", parameters = {
            @Parameter(description = "父级部门 id", example = "0")
    })
    public R<List<Tree<Long>>> tree(@RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        List<Tree<Long>> tree = sysDeptService.getTreeByParentId(parentId);
        return R.ok(tree);
    }

}

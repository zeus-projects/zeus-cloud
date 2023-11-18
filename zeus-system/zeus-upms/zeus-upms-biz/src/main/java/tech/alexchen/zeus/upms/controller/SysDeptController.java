package tech.alexchen.zeus.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.entity.SysDept;
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

    @PostMapping
    @Operation(summary = "创建部门", description = "创建部门接口，创建成功后返回部门 id")
    public R<Long> save(@Valid @RequestBody SysDept entity) {
        sysDeptService.saveDept(entity);
        return R.ok(entity.getId());
    }

    @PutMapping
    @Operation(summary = "更新部门", description = "更新部门信息")
    public R<Boolean> update(@Valid @RequestBody SysDept entity) {
        return R.bool(sysDeptService.updateDept(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    @Parameter(description = "部门 id", required = true, in = ParameterIn.PATH)
    public R<Boolean> removeById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        return R.bool(sysDeptService.removeDept(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单个部门")
    public R<SysDept> getById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        SysDept dept = sysDeptService.getById(id);
        return R.ok(dept);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询部门")
    @Parameters(value = {
            @Parameter(name = "size", description = "每页显示条数，默认 10", example = "10"),
            @Parameter(name = "current", description = "当前页，默认 1", example = "1"),
            @Parameter(name = "name", description = "部门名称"),
            @Parameter(name = "page", hidden = true),
            @Parameter(name = "dto", hidden = true)
    })
    public R<Page<SysDept>> page(Page<SysDept> page, SysDept dto) {
        Page<SysDept> pageRes = sysDeptService.pageDept(page, dto);
        return R.ok(pageRes);
    }

    @GetMapping("/list")
    @Operation(summary = "列表查询部门")
    public R<List<SysDept>> list() {
        return R.ok(sysDeptService.list());
    }

    @GetMapping("/tree")
    @Operation(summary = "查询部门列表树")
    public R<List<Tree<Long>>> tree(Long parentId) {
        List<Tree<Long>> tree = sysDeptService.getDeptTreeByParentId(parentId);
        return R.ok(tree);
    }

}

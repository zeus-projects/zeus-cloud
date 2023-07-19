package tech.alexchen.zeus.upms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.core.validation.SaveGroup;
import tech.alexchen.zeus.common.core.validation.UpdateGroup;
import tech.alexchen.zeus.upms.api.dto.SysDeptDTO;
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
@Tag(name = "系统管理 - 部门")
@RestController
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService sysDeptService;
    
    private final SysDeptConverter converter;

    @PostMapping
    @Operation(summary = "创建部门")
    public R<Long> save(@Validated(SaveGroup.class) @RequestBody SysDeptDTO dto) {
        Long id = sysDeptService.saveDept(converter.toEntity(dto));
        return R.ok(id);
    }

    @PutMapping
    @Operation(summary = "更新部门")
    public R<Boolean> update(@Validated(UpdateGroup.class) @RequestBody SysDeptDTO dto) {
        return R.ok(sysDeptService.updateById(converter.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    public R<Boolean> removeById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        return R.ok(sysDeptService.removeDept(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单个部门")
    public R<SysDept> getById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        SysDept dept = sysDeptService.getById(id);
        return R.ok(dept);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询部门")
    public R<Page<SysDept>> page(Page page, SysDeptDTO dto) {
        Page<SysDept> pageRes = sysDeptService.pageDept(page, converter.toEntity(dto));
        return R.ok(pageRes);
    }

    @GetMapping("/list")
    @Operation(summary = "列表查询部门")
    public R<List<SysDept>> list() {
        return R.ok(sysDeptService.list());
    }

}

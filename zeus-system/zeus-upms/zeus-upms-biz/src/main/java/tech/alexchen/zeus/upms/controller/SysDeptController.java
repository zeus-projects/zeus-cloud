package tech.alexchen.zeus.upms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "系统管理 - 部门")
@RestController
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService sysDeptService;
    
    private final SysDeptConverter converter;

    @PostMapping
    @ApiOperation("创建部门")
    public R<Long> save(@Validated(SaveGroup.class) @RequestBody SysDeptDTO dto) {
        Long id = sysDeptService.saveDept(converter.toEntity(dto));
        return R.ok(id);
    }

    @PutMapping
    @ApiOperation("更新部门")
    public R<Boolean> update(@Validated(UpdateGroup.class) @RequestBody SysDeptDTO dto) {
        return R.ok(sysDeptService.updateById(converter.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除部门")
    public R<Boolean> removeById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        return R.ok(sysDeptService.removeDept(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询单个部门")
    public R<SysDept> getById(@PathVariable @Valid @NotNull(message = "部门 id 不能为空") Long id) {
        SysDept dept = sysDeptService.getById(id);
        return R.ok(dept);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询部门")
    public R<Page<SysDept>> page(Page page, SysDeptDTO dto) {
        Page<SysDept> pageRes = sysDeptService.pageDept(page, converter.toEntity(dto));
        return R.ok(pageRes);
    }

    @GetMapping("/list")
    @ApiOperation("列表查询部门")
    public R<List<SysDept>> list() {
        return R.ok(sysDeptService.list());
    }

}

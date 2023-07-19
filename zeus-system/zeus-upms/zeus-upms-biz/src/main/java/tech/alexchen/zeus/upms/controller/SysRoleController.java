package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysRoleDTO;
import tech.alexchen.zeus.upms.convert.SysRoleConverter;
import tech.alexchen.zeus.upms.service.SysRoleService;

import javax.validation.Valid;

/**
 * @author alexchen
 */
@Tag(name = "系统管理 - 角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService service;
    private final SysRoleConverter converter;

    @Operation(summary = "创建角色")
    @PostMapping
    public R<Long> save(@Valid @RequestBody SysRoleDTO dto) {
        return R.ok(service.saveRole(converter.toEntity(dto)));
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody SysRoleDTO dto) {
        service.updateRole(converter.toEntity(dto));
        return R.ok(true);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        service.removeRoleById(id);
        return R.ok(true);
    }


}

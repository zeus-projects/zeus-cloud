package tech.alexchen.zeus.upms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("系统管理 - 角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService service;
    private final SysRoleConverter converter;

    @ApiOperation("创建角色")
    @PostMapping
    public R<Long> save(@Valid @RequestBody SysRoleDTO dto) {
        return R.ok(service.saveRole(converter.toEntity(dto)));
    }

    @ApiOperation("更新角色")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody SysRoleDTO dto) {
        service.updateRole(converter.toEntity(dto));
        return R.ok(true);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        service.removeRoleById(id);
        return R.ok(true);
    }


}

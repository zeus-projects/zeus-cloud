package tech.alexchen.zeus.upms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysUserDTO;
import tech.alexchen.zeus.upms.convert.SysUserConverter;
import tech.alexchen.zeus.upms.entity.SysUser;
import tech.alexchen.zeus.upms.service.SysUserService;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author alexchen
 */
@Api("系统管理 - 用户")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysUserConverter converter;

    @ApiOperation("创建用户")
    @PostMapping
    public R<Long> saveUser(@Valid @RequestBody SysUserDTO dto) {
        return R.ok(sysUserService.saveUser(converter.toEntity(dto)));
    }

    @ApiOperation("修改用户")
    @PutMapping
    public R<Boolean> updateUser(@Valid @RequestBody SysUserDTO dto) {
        sysUserService.updateUser(converter.toEntity(dto));
        return R.ok(true);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public R<Boolean> removeUser(@PathVariable Long id) {
        sysUserService.removeUserById(id);
        return R.ok(true);
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/{id}")
    public R<SysUser> getUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return R.ok(sysUser);
    }

}

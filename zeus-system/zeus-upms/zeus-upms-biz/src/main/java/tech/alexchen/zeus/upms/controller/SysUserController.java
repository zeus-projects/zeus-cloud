package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.entity.SysUser;
import tech.alexchen.zeus.upms.service.SysUserService;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author alexchen
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @Operation(summary = "创建用户")
    @PostMapping
    public R<Long> saveUser(@Valid @RequestBody SysUser entity) {
        return R.ok(sysUserService.saveUser(entity));
    }

    @Operation(summary = "更新用户")
    @PutMapping
    public R<Boolean> updateUser(@Valid @RequestBody SysUser entity) {
        return R.bool(sysUserService.updateUser(entity), "更新失败");
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public R<Boolean> removeUser(@PathVariable Long id) {
        return R.bool(sysUserService.removeUserById(id), "删除失败");
    }

    @Operation(summary = "查询单个用户")
    @GetMapping("/{id}")
    public R<SysUser> getUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return R.ok(sysUser);
    }

}

package tech.alexchen.zeus.upms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.entity.SysRole;
import tech.alexchen.zeus.upms.service.SysRoleService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alexchen
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService service;

    @Operation(summary = "创建角色")
    @PostMapping
    public R<Long> save(@Valid @RequestBody SysRole entity) {
        service.saveRole(entity);
        return R.ok(entity.getId());
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody SysRole entity) {
        return R.bool(service.updateRoleById(entity), "更新失败");
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.bool(service.removeRoleById(id), "删除失败");
    }

    @Operation(summary = "角色分页列表")
    @GetMapping("/page")
    public R<Page<SysRole>> page(Page<SysRole> page) {
        return R.ok(service.page(page));
    }

    @Operation(summary = "更新操作权限")
    @PutMapping("/menu")
    public R<Boolean> updateRoleMenu(Long roleId, List<Long> menus) {
        return R.bool(service.updateRoleMenus(roleId, menus), "更新失败");
    }
}

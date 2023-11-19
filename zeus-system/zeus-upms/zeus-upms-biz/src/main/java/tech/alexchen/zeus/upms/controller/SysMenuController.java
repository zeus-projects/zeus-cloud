package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.core.validation.UpdateGroup;
import tech.alexchen.zeus.upms.api.entity.SysMenu;
import tech.alexchen.zeus.upms.service.SysMenuService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author alexchen
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService menuService;

    @Operation(summary = "创建菜单")
    @PostMapping
    public R<Long> save(@Valid @RequestBody SysMenu entity) {
        menuService.saveMenu(entity);
        return R.ok(entity.getId());
    }

    @Operation(summary = "更新菜单")
    @PutMapping
    public R<Boolean> update(@Validated(UpdateGroup.class) @RequestBody SysMenu entity) {
        return R.bool(menuService.updateMenu(entity), "更新失败");
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable @Valid @NotNull Long id) {
        return R.bool(menuService.removeMenuById(id), "删除失败");
    }

    @Operation(summary = "查询菜单")
    @GetMapping("/list")
    public R<List<SysMenu>> list() {
        return R.ok(menuService.list());
    }
}

package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysMenu;
import tech.alexchen.zeus.upms.api.vo.SysMenuVO;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.service.SysMenuService;

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
    private final SysMenuConverter converter;

    /**
     * 创建菜单
     */
    @PostMapping
    @Operation(summary = "创建菜单")
    public R<Long> save(@Valid @RequestBody SysMenuSaveDTO dto) {
        return R.ok(menuService.saveMenu(dto));
    }

    /**
     * 更新菜单
     */
    @PutMapping
    @Operation(summary = "更新菜单")
    public R<Boolean> update(@Valid @RequestBody SysMenuUpdateDTO dto) {
        menuService.updateMenu(dto);
        return R.ok(true);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜单")
    public R<Boolean> remove(@PathVariable @Valid @NotNull Long id) {
        menuService.removeMenuById(id);
        return R.ok(true);
    }

    /**
     * 查询菜单详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询菜单详情")
    public R<SysMenuVO> getMenuById(@PathVariable @Valid @NotNull Long id) {
        SysMenu menu = menuService.getMenuById(id);
        return R.ok(converter.toVO(menu));
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询菜单列表")
    public R<List<SysMenuVO>> list() {
        List<SysMenu> menus = menuService.getMenuList();
        return R.ok(converter.toSysMenuVOList(menus));
    }
}

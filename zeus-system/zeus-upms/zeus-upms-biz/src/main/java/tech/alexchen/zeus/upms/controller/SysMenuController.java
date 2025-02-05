package tech.alexchen.zeus.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysMenuSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysMenuUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysMenuVO;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.entity.SysMenu;
import tech.alexchen.zeus.upms.service.SysMenuService;

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
    @DeleteMapping
    @Operation(summary = "删除菜单")
    public R<Boolean> remove(@RequestParam("id") Long id) {
        menuService.removeMenuById(id);
        return R.ok(true);
    }

    /**
     * 查询菜单详情
     */
    @GetMapping
    @Operation(summary = "查询菜单详情")
    public R<SysMenuVO> getMenuById(@RequestParam("id") Long id) {
        SysMenu menu = menuService.getMenuById(id);
        if (menu != null) {
            return R.ok(converter.toVO(menu));
        }
        return R.fail("菜单未找到", null);
    }

    /**
     * 查询菜单树
     */
    @GetMapping("/tree")
    @Operation(summary = "查询菜单树")
    public R<List<Tree<Long>>> getMenuTree(@RequestParam("parentId") Long parentId) {
        return R.ok(menuService.getMenuTree(parentId));
    }
}

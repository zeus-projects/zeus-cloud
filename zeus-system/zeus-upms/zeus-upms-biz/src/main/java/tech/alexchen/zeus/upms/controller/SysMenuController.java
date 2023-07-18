package tech.alexchen.zeus.upms.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.core.validation.SaveGroup;
import tech.alexchen.zeus.common.core.validation.UpdateGroup;
import tech.alexchen.zeus.upms.api.dto.SysMenuDTO;
import tech.alexchen.zeus.upms.api.vo.SysMenuVO;
import tech.alexchen.zeus.upms.convert.SysMenuConverter;
import tech.alexchen.zeus.upms.entity.SysMenu;
import tech.alexchen.zeus.upms.service.SysMenuService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author alexchen
 */
@Tag(name = "菜单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysMenuController {

    private final SysMenuService menuService;

    private final SysMenuConverter menuConverter;

    @ApiOperation("创建菜单")
    @PostMapping
    public R<Long> save(@Validated(SaveGroup.class) @RequestBody SysMenuDTO dto) {
        return R.ok(menuService.saveMenu(menuConverter.toEntity(dto)));
    }

    @ApiOperation("更新菜单")
    @PutMapping
    public R<Boolean> update(@Validated(UpdateGroup.class) @RequestBody SysMenuDTO dto) {
        menuService.updateMenu(menuConverter.toEntity(dto));
        return R.ok(true);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable @Valid @NotNull Long id) {
        menuService.removeMenuById(id);
        return R.ok(true);
    }

    @ApiOperation("查询菜单")
    @GetMapping("/list")
    public R<List<SysMenuVO>> list() {
        List<SysMenu> menuList = menuService.list();
        List<SysMenuVO> voList = menuConverter.toSysMenuVOList(menuList);
        return R.ok(voList);
    }

}

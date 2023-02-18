package tech.alexchen.zeus.upms.controller.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuResponseVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.menu.MenuUpdateVO;
import tech.alexchen.zeus.upms.convert.permission.MenuConvert;
import tech.alexchen.zeus.upms.service.permission.MenuService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alexchen
 */
@Api("系统管理 - 菜单")
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @ApiOperation("创建菜单")
    @PostMapping
    public R<Long> save(@Valid @RequestBody MenuSaveVO vo) {
        return R.ok(menuService.saveMenu(vo));
    }

    @ApiOperation("更新菜单")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody MenuUpdateVO vo) {
        menuService.updateMenu(vo);
        return R.ok(true);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable Long id) {
        menuService.removeMenuById(id);
        return R.ok(true);
    }

    @ApiOperation("查询菜单")
    @GetMapping("/list")
    public R<List<MenuResponseVO>> list() {
        List<MenuResponseVO> res = MenuConvert.INSTANCE.convertList(menuService.list());
        return R.ok(res);
    }

}

package tech.alexchen.zeus.upms.controller.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleSaveVO;
import tech.alexchen.zeus.upms.controller.role.vo.role.RoleUpdateVO;
import tech.alexchen.zeus.upms.service.permission.RoleService;

import javax.validation.Valid;

/**
 * @author alexchen
 */
@Api("系统管理 - 角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("创建角色")
    @PostMapping
    public R<Long> save(@Valid @RequestBody RoleSaveVO vo) {
        return R.ok(roleService.saveRole(vo));
    }

    @ApiOperation("更新角色")
    @PutMapping
    public R<Boolean> update(@Valid @RequestBody RoleUpdateVO vo) {
        roleService.updateRole(vo);
        return R.ok(true);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        roleService.removeRoleById(id);
        return R.ok(true);
    }


}

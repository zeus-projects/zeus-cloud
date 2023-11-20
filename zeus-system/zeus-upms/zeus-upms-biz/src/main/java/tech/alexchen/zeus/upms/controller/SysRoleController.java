package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.api.entity.SysRole;
import tech.alexchen.zeus.upms.api.vo.SysRoleVO;
import tech.alexchen.zeus.upms.convert.SysRoleConverter;
import tech.alexchen.zeus.upms.service.SysRoleService;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author alexchen
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService service;
    private final SysRoleConverter converter;

    /**
     * 创建角色
     */
    @PostMapping
    @Operation(summary = "创建角色")
    public R<Long> save(@Valid @RequestBody SysRoleSaveDTO dto) {
        return R.ok(service.saveRole(dto));
    }

    /**
     * 更新角色
     */
    @PutMapping
    @Operation(summary = "更新角色")
    public R<Boolean> update(@Valid @RequestBody SysRoleUpdateDTO dto) {
        service.updateRoleById(dto);
        return R.ok(true);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    public R<Boolean> removeById(@PathVariable Long id) {
        service.removeRoleById(id);
        return R.ok(true);
    }

    /**
     * 角色分页列表
     */
    @GetMapping("/page")
    @Operation(summary = "角色分页列表")
    public R<PageResult<SysRoleVO>> page(PageParam page) {
        PageResult<SysRole> pageResult = service.getDeptPage(page);
        PageResult<SysRoleVO> pageVO = converter.toPageVO(pageResult);
        return R.ok(pageVO);
    }

    /**
     * 更新角色的菜单权限
     */
    @PutMapping("/menu")
    @Operation(summary = "更新菜单权限")
    public R<Boolean> updateRoleMenu(Long roleId, Set<Long> menus) {
        service.updateRoleMenus(roleId, menus);
        return R.ok(true);
    }

}

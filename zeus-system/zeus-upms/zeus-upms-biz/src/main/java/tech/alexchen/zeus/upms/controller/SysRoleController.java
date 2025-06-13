package tech.alexchen.zeus.upms.controller;

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
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysRoleSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysRoleUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysRoleVO;
import tech.alexchen.zeus.upms.convert.SysRoleConverter;
import tech.alexchen.zeus.upms.entity.SysRole;
import tech.alexchen.zeus.upms.service.SysRoleService;

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
     * 新增角色
     */
    @PostMapping
    @Operation(summary = "新增角色")
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
    @DeleteMapping
    @Operation(summary = "删除角色")
    public R<Boolean> removeById(@RequestParam(value = "id") Long id) {
        service.removeRoleById(id);
        return R.ok(true);
    }

    /**
     * 查询角色
     */
    @GetMapping
    @Operation(summary = "查询角色")
    public R<SysRoleVO> getById(@RequestParam(value = "id") Long id) {
        SysRole role = service.getById(id);
        return R.ok(converter.toVO(role));
    }

    /**
     * 角色分页列表
     */
    @GetMapping("/page")
    @Operation(summary = "角色分页列表")
    public R<PageResult<SysRoleVO>> page(PageParam page) {
        PageResult<SysRole> pageResult = service.getRolePage(page);
        PageResult<SysRoleVO> pageVO = converter.toPageVO(pageResult);
        return R.ok(pageVO);
    }

    /**
     * 更新角色的菜单权限
     */
    @GetMapping("/menus")
    @Operation(summary = "获取角色的菜单ID列表")
    public R<Set<Long>> getRoleMenus(@RequestParam(value = "id") Long id) {
        return R.ok(service.getRoleMenus(id));
    }

    /**
     * 更新角色的菜单权限
     */
    @PutMapping("/menus")
    @Operation(summary = "更新角色的菜单权限")
    public R<Boolean> updateRoleMenu(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "menus") Set<Long> menus) {
        service.updateRoleMenus(id, menus);
        return R.ok(true);
    }

}

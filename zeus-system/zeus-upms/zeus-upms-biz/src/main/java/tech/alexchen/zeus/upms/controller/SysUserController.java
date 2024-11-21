package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.api.vo.SysUserVO;
import tech.alexchen.zeus.upms.convert.SysUserConverter;
import tech.alexchen.zeus.upms.entity.SysUser;
import tech.alexchen.zeus.upms.service.SysUserService;


/**
 * 用户管理
 *
 * @author alexchen
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysUserConverter converter;

    /**
     * 创建用户
     */
    @PostMapping
    @Operation(summary = "创建用户")
    public R<Long> saveUser(@Valid @RequestBody SysUserSaveDTO dto) {
        return R.ok(sysUserService.saveUser(dto));
    }

    /**
     * 更新用户
     */
    @PutMapping
    @Operation(summary = "更新用户")
    public R<Boolean> updateUser(@Valid @RequestBody SysUserUpdateDTO dto) {
        sysUserService.updateUser(dto);
        return R.ok(true);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public R<Boolean> removeUser(@PathVariable Long id) {
        sysUserService.removeUserById(id);
        return R.ok(true);
    }

    /**
     * 根据用户 id 查询用户信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID查询")
    public R<SysUserVO> getUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getUserById(id);
        return R.ok(converter.toVO(sysUser));
    }

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/name/{username}")
    @Operation(summary = "根据用户名查询")
    public R<SysUserAuthDTO> getUserByName(@PathVariable String username) {
        SysUser sysUser = sysUserService.getUserByName(username);
        return R.ok(converter.toAuthDTO(sysUser));
    }

    /**
     * 根据用户名查询用户授权信息
     */
    @GetMapping("/auth/{username}")
    @Operation(summary = "查询用户授权信息")
    public R<SysUserAuthDTO> getUserAuthInfo(@PathVariable String username) {
        return R.ok(sysUserService.getUserAuthInfo(username));
    }

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户")
    public R<PageResult<SysUserVO>> getUserPage(PageParam pageParam) {
        PageResult<SysUser> userPage = sysUserService.getUserPage(pageParam);
        PageResult<SysUserVO> pageVO = converter.toPageVO(userPage);
        return R.ok(pageVO);
    }
}

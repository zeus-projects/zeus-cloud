package tech.alexchen.zeus.upms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
import tech.alexchen.zeus.common.core.utils.IpUtil;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.common.security.resource.annotation.Inner;
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
    @DeleteMapping
    @Operation(summary = "删除用户")
    public R<Boolean> removeUser(@RequestParam(value = "id") Long id) {
        sysUserService.removeUserById(id);
        return R.ok(true);
    }

    /**
     * 根据用户 id 查询用户信息
     */
    @GetMapping
    @Operation(summary = "根据用户ID查询")
    public R<SysUserVO> getUserById(@RequestParam(value = "id") Long id) {
        SysUser sysUser = sysUserService.getUserById(id);
        return R.ok(converter.toVO(sysUser));
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

    /**
     * 查询当前登录用户的信息
     */
    @GetMapping("/auth/current-user")
    @Operation(summary = "查询当前登录用户的信息")
    public R<SysUserAuthDTO> getCurrentUserInfo() {
        SysUserAuthDTO currentUserInfo = sysUserService.getCurrentUserInfo();
        currentUserInfo.setPassword(null);
        return R.ok(currentUserInfo);
    }

    // ----------- 内部调用接口 -----------

    /**
     * 根据用户名查询授权信息-仅内部调用
     */
    @Inner
    @GetMapping("/auth/username")
    @Operation(summary = "根据用户名查询授权信息-内部调用", hidden = true)
    public R<SysUserAuthDTO> getUserAuthInfoByUsername(@RequestParam(value = "username") String username) {
        return R.ok(sysUserService.getUserAuthInfo(username));
    }

    /**
     * 更新最后登录信息-仅内部调用
     */
    @Inner
    @PutMapping("/last-login")
    @Operation(summary = "更新最后登录信息-内部调用", hidden = true)
    public R<Boolean> updateLastLoginInfo(@RequestParam(value = "userId") Long userId,
                                          HttpServletRequest request) {
        String clientIp = IpUtil.getClientIp(request);
        sysUserService.updateLastLoginInfo(userId, clientIp);
        return R.ok(true);
    }

}

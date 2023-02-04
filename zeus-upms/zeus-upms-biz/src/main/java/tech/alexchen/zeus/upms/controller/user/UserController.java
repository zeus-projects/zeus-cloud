package tech.alexchen.zeus.upms.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.user.vo.UserResponseVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserSaveVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserUpdateVO;
import tech.alexchen.zeus.upms.convert.user.UserConvert;
import tech.alexchen.zeus.upms.domain.user.UserDO;
import tech.alexchen.zeus.upms.service.user.UserService;

import javax.validation.Valid;

/**
 * 用户管理
 *
 * @author alexchen
 */
@Api("系统管理 - 用户")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("创建用户")
    @PostMapping
    public R<Long> saveUser(@Valid @RequestBody UserSaveVO vo) {
        return R.ok(userService.saveUser(vo));
    }

    @ApiOperation("修改用户")
    @PutMapping
    public R<Boolean> updateUser(@Valid @RequestBody UserUpdateVO vo) {
        userService.updateUser(vo);
        return R.ok(true);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public R<Boolean> removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return R.ok(true);
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/{id}")
    public R<UserResponseVO> getUserById(@PathVariable Long id) {
        UserDO userDO = userService.getById(id);
        UserResponseVO user = UserConvert.INSTANCE.convertResponse(userDO);
        return R.ok(user);
    }

}

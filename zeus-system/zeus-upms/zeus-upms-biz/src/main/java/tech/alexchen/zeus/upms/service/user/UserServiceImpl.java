package tech.alexchen.zeus.upms.service.user;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.exception.ExceptionUtil;
import tech.alexchen.zeus.upms.controller.user.vo.UserSaveVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserUpdateVO;
import tech.alexchen.zeus.upms.convert.user.UserConverter;
import tech.alexchen.zeus.upms.domain.user.UserDO;
import tech.alexchen.zeus.upms.mapper.user.UserMapper;

import static tech.alexchen.zeus.upms.enums.ErrorCodeConstants.USER_PHONE_EXISTS;
import static tech.alexchen.zeus.upms.enums.ErrorCodeConstants.USER_USERNAME_EXISTS;

/**
 * @author alexchen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public Long saveUser(UserSaveVO vo) {
        // 检查用户名是否重复
        checkUsernameUnique(null, vo.getUsername());
        // 检查手机号码是否重复
        checkPhoneUnique(null, vo.getPhone());
        // TODO
        UserDO user = UserConverter.INSTANCE.convertFromSave(vo);
        this.save(user);
        return user.getId();
    }

    @Override
    public void updateUser(UserUpdateVO vo) {
        // 检查手机号码是否重复
        checkPhoneUnique(vo.getId(), vo.getPhone());
        // 检查手机号码是否重复
        checkPhoneUnique(vo.getId(), vo.getPhone());
        UserDO user = UserConverter.INSTANCE.convertFromUpdate(vo);
        this.updateById(user);
    }

    @Override
    public void removeUserById(Long id) {
        // TODO
        this.removeById(id);
    }

    /**
     * 检查用户名是否重复
     * @param id 用户id
     * @param username 用户名
     */
    private void checkUsernameUnique(@Nullable Long id, String username) {
        // 更新时可能为空？
        if (StrUtil.isBlank(username)) {
            return;
        }
        UserDO user = this.getBaseMapper().selectByUsername(username);
        if (user == null) {
            return;
        }
        if (id == null || !user.getId().equals(id)) {
            throw ExceptionUtil.exception(USER_USERNAME_EXISTS);
        }
    }

    /**
     * 检查用户手机号码是否重复
     * @param id 用户 id
     * @param phone 手机号码
     */
    private void checkPhoneUnique(@Nullable Long id, String phone) {
        if (StrUtil.isBlank(phone)) {
            return;
        }
        UserDO user = this.getBaseMapper().selectByPhone(phone);
        if (id == null || !user.getPhone().equals(phone)) {
            throw ExceptionUtil.exception(USER_PHONE_EXISTS);
        }
    }

}

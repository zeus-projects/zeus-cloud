package tech.alexchen.zeus.upms.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.user.vo.UserSaveVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserUpdateVO;
import tech.alexchen.zeus.upms.entity.user.UserDO;

/**
 * @author alexchen
 */
public interface UserService extends IService<UserDO> {

    /**
     * 添加用户
     *
     * @param vo 用户信息
     * @return 用户 id
     */
    Long saveUser(UserSaveVO vo);

    /**
     * 修改用户
     * @param vo 用户信息
     */
    void updateUser(UserUpdateVO vo);

    /**
     * @param id 用户 id
     */
    void removeUserById(Long id);
}

package tech.alexchen.zeus.upms.service.user;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.domain.user.UserDO;
import tech.alexchen.zeus.upms.mapper.user.UserMapper;

/**
 * @author alexchen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {


}

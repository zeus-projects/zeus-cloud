package tech.alexchen.zeus.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.auth.entity.AuthUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author alexchen
 * @date 2023/2/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public AuthUser getUserInfoByUsername(String username) {
        // TODO 应该使用 Feign 调用 zeus-upms-api 的接口，从 upms 模块查询用户信息
        //
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(roles.toArray(new String[0]));
        AuthUser user = new AuthUser("admin", passwordEncoder.encode("123456"), authorities);
        return user;
    }

}

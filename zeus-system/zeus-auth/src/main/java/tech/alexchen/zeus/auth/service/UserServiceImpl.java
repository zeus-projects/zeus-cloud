package tech.alexchen.zeus.auth.service;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.auth.entity.AuthUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

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
        AuthUser user = new AuthUser();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("111"));
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        user.setRoles(roles);
        return user;
    }
}

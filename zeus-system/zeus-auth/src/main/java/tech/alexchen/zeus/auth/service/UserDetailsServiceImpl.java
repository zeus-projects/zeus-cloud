package tech.alexchen.zeus.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.auth.entity.AuthUser;

import javax.annotation.Resource;

/**
 * @author alexchen
 * @date 2023/2/15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 验证用户信息
        AuthUser authUser = userService.getUserInfoByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException("username not found");
        }
        //定义权限列表，用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头、
//        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authUser.getAuthorities().toArray(new String[0]));

        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        User userDetails = new User(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
        return userDetails;
    }

}





















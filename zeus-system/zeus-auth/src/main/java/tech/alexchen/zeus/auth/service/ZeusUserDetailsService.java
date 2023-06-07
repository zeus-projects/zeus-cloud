package tech.alexchen.zeus.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.alexchen.zeus.common.security.component.AuthUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author alexchen
 */
//@Service
public class ZeusUserDetailsService implements UserDetailsService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 验证用户信息
        AuthUser authUser = this.getUserInfoByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException("username not found");
        }
        // 定义权限列表，用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头、
        // List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authUser.getAuthorities().toArray(new String[0]));
        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        return new User(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
    }

    public AuthUser getUserInfoByUsername(String username) {
        // TODO 应该使用 Feign 调用 zeus-upms-api 的接口，从 upms 模块查询用户信息
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(roles.toArray(new String[0]));
        return new AuthUser("admin", passwordEncoder.encode("123456"), authorities);
    }
}





















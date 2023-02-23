package tech.alexchen.zeus.auth.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author alexchen
 * @date 2023/2/15
 */
public class AuthUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private Long id;

    /**
     * 部门ID
     */
    @Getter
    private Long deptId;

    /**
     * 手机号
     */
    @Getter
    private String phone;

    /**
     * 头像
     */
    @Getter
    private String avatar;

    /**
     * 租户ID
     */
    @Getter
    private Long tenantId;

    /**
     * 拓展字段:昵称
     */
    @Getter
    private String nickname;

    /**
     * 拓展字段:邮箱
     */
    @Getter
    private String email;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}

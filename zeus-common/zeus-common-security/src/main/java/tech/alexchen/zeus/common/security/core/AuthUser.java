package tech.alexchen.zeus.common.security.core;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author alexchen
 */
@Getter
public class AuthUser extends User {

    private final Long id;

    private final String phone;

    public AuthUser(Long id, String username, String password, String phone,  Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.phone = phone;
    }

    public AuthUser(Long id, String username, String password, String phone, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.phone = phone;
    }

}

package tech.alexchen.zeus.auth.security;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.security.core.AuthUser;
import tech.alexchen.zeus.upms.api.constant.SysConstant;
import tech.alexchen.zeus.upms.api.constant.UpmsResponseCode;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.interfaces.RemoteSysUserService;

import java.util.List;

@Primary
@Component
public class ZeusUserDetailsService implements UserDetailsService {

    @Resource
    private RemoteSysUserService userService;

    /**
     * 加密算法前缀
     */
    public final static String BCRYPT = "{bcrypt}";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<SysUserAuthDTO> res = userService.getUserAuthInfo(username);
        if (res.isFailed() || res.getData() == null) {
            throw new UsernameNotFoundException(StrUtil.format("Username '{}' not found", username));
        }
        SysUserAuthDTO user = res.getData();
        if (SysConstant.USER_STATUS_LOCKED.equals(user.getStatus())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_USER_LOCKED);
        }
        String password = BCRYPT + user.getPassword();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getPermissions());

        return new AuthUser(user.getId(), user.getUsername(), password, user.getPhone(),
                true, true, true, true, authorities
        );
    }

}

package tech.alexchen.zeus.common.security.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;

/**
 * @author alexchen
 */
public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static AuthUser getUser() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof AuthUser) {
            return (AuthUser) principal;
        }
        return null;
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = SecurityUtil.getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getAuthorities();
    }

    public static boolean getInnerValue() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
        String innerHeader = request.getHeader(SecurityConstants.INNER);
        return Boolean.parseBoolean(innerHeader);
    }
}

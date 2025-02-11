package tech.alexchen.zeus.common.security.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Map;

/**
 * @author alexchen
 */
public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Map<String, Object> getAuthUserClaim() {
        OAuth2IntrospectionAuthenticatedPrincipal principal =
                (OAuth2IntrospectionAuthenticatedPrincipal)getAuthentication().getPrincipal();
        Map<String, Object> authUserClaim =  principal.getClaim(SecurityConstants.AUTH_USER_CLAIM);
        if (authUserClaim == null) {
            throw new OAuth2IntrospectionException("Get 'authUser' claim from token principal failed");
        }
        return authUserClaim;
    }

    public static String getUsername() {
        Map<String, Object> authUserClaim = getAuthUserClaim();
        return authUserClaim.get(OAuth2TokenIntrospectionClaimNames.USERNAME).toString();
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
        assert attributes != null;
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
        String innerHeader = request.getHeader(SecurityConstants.INNER);
        return Boolean.parseBoolean(innerHeader);
    }
}

package tech.alexchen.zeus.auth.oauth2.password;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author alexchen
 */
@Getter
public class OAuth2UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 授权类型
     */
    private final AuthorizationGrantType authorizationGrantType;

    /**
     * 认证信息
     */
    private final Authentication clientPrincipal;

    /**
     * 权限范围
     */
    private final Set<String> scopes;

    /**
     * 扩展参数
     */
    private final Map<String, Object> additionalParameters;

    public OAuth2UsernamePasswordAuthenticationToken(AuthorizationGrantType authorizationGrantType,
                                                     Authentication clientPrincipal,
                                                     Set<String> scopes,
                                                     Map<String, Object> additionalParameters) {
        super(Collections.emptySet());
        Assert.notNull(authorizationGrantType, "authorizationGrantType cannot be null");
        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
        this.authorizationGrantType = authorizationGrantType;
        this.clientPrincipal = clientPrincipal;
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
        this.additionalParameters = Collections.unmodifiableMap(
                additionalParameters != null ? new HashMap<>(additionalParameters) : Collections.emptyMap());
    }

    @Override
    public Object getCredentials() {
        // 密码置空，不返回到 token 中
        return "";
    }

    public Object getPrincipal() {
        return this.clientPrincipal;
    }

}

package tech.alexchen.zeus.auth.oauth2.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author alexchen
 */
@Slf4j
public class OAuth2UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;

    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    private final AuthenticationManager authenticationManager;

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.2.1";

    public OAuth2UsernamePasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                                        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                                        AuthenticationManager authenticationManager) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证信息
        OAuth2UsernamePasswordAuthenticationToken authenticationToken = (OAuth2UsernamePasswordAuthenticationToken) authentication;

        // 获取并判断 client 信息
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal == null || !clientPrincipal.isAuthenticated()) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
        }
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
        assert registeredClient != null;
        if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        // 从认证信息中获取 scope 权限信息
        Set<String> authorizedScopes = getScopes(authenticationToken, registeredClient);

        // 解析用户名和密码参数
        Map<String, Object> reqParameters = authenticationToken.getAdditionalParameters();
        String username = (String) reqParameters.get(OAuth2ParameterNames.USERNAME);
        String password = (String) reqParameters.get(OAuth2ParameterNames.PASSWORD);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        log.debug("got usernamePasswordAuthenticationToken = {}", usernamePasswordAuthenticationToken);

        // spring security 的 authenticationManager 进行认证
        Authentication usernamePasswordAuthentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        // 构建 tokenContextBuilder，用于生成 tokenContext，然后用它获取 token
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(usernamePasswordAuthentication)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrant(authenticationToken);

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization
                .withRegisteredClient(registeredClient)
                .principalName(usernamePasswordAuthentication.getName())
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                // 0.4.0 新增的方法
                .authorizedScopes(authorizedScopes);

        // ----- Access token -----
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                    "The token generator failed to generate the access token.", ERROR_URI);
            throw new OAuth2AuthenticationException(error);
        }
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.id(accessToken.getTokenValue())
                    .token(accessToken,
                            (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME,
                                    ((ClaimAccessor) generatedAccessToken).getClaims()))
                    // 0.4.0 新增的方法
                    .authorizedScopes(authorizedScopes)
                    .attribute(Principal.class.getName(), usernamePasswordAuthentication);
        } else {
            authorizationBuilder.id(accessToken.getTokenValue()).accessToken(accessToken);
        }

        // ----- Refresh token -----
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                // Do not issue refresh token to public client
                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the refresh token.", ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }
            if (log.isTraceEnabled()) {
                log.trace("Generated refresh token");
            }

            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            authorizationBuilder.refreshToken(refreshToken);
        }

        // 存储 token 信息，具体实现由注入的 authorizationService 决定
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);

        log.debug("returning OAuth2AccessTokenAuthenticationToken");

        // 返回 token
        return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken,
                refreshToken, Objects.requireNonNull(authorization.getAccessToken().getClaims()));
    }


    private static Set<String> getScopes(OAuth2UsernamePasswordAuthenticationToken authenticationToken, RegisteredClient registeredClient) {
        Set<String> authorizedScopes;
        if (!CollectionUtils.isEmpty(authenticationToken.getScopes())) {
            for (String requestedScope : authenticationToken.getScopes()) {
                if (!registeredClient.getScopes().contains(requestedScope)) {
                    throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
                }
            }
            authorizedScopes = new LinkedHashSet<>(authenticationToken.getScopes());
        } else {
            authorizedScopes = new LinkedHashSet<>();
        }
        return authorizedScopes;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 仅支持自定义的 token 类型
        return OAuth2UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

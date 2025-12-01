package tech.alexchen.zeus.auth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeRequestAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import tech.alexchen.zeus.auth.oauth2.password.OAuth2UsernamePasswordAuthenticationConverter;
import tech.alexchen.zeus.auth.oauth2.password.OAuth2UsernamePasswordAuthenticationProvider;
import tech.alexchen.zeus.auth.oauth2.token.UUIDOAuth2AccessTokenGenerator;
import tech.alexchen.zeus.auth.oauth2.token.UUIDOAuth2RefreshTokenGenerator;
import tech.alexchen.zeus.auth.oauth2.token.ZeusOAuth2TokenCustomizer;
import tech.alexchen.zeus.auth.security.ZeusAuthenticationFailureHandler;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;

/**
 * 授权服务器配置
 *
 * @author alexchen
 */
@Configuration
@EnableConfigurationProperties(AuthorizationProperties.class)
@AllArgsConstructor
public class AuthorizationServerConfiguration {

    private final AuthorizationProperties authorizationProperties;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // 里面指定了 securityMatcher
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults())
                .authorizationServerSettings(
                        // 指明为域名，就不会自动配置为本机的内网 ip，防止 client 和 resource 因为端点不一致导致的错误
                        AuthorizationServerSettings.builder().issuer(authorizationProperties.getIssuerUrl()).build()
                )
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                .errorResponseHandler(new ZeusAuthenticationFailureHandler())
                )
                .tokenEndpoint((tokenEndpoint) -> tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter())
//                        .accessTokenResponseHandler(new ZeusAuthenticationSuccessHandler())
                        .errorResponseHandler(new ZeusAuthenticationFailureHandler())
                )
        ;
        http.csrf(AbstractHttpConfigurer::disable);
        // 先 build，后面才可以通过 http.getSharedObject 方法获取到 AuthenticationManager
        DefaultSecurityFilterChain securityFilterChain = http.build();

        addCustomOAuth2GrantAuthenticationProvider(http);
        return securityFilterChain;
    }

    public AuthenticationConverter accessTokenRequestConverter() {
        return new DelegatingAuthenticationConverter(Arrays.asList(
                new OAuth2UsernamePasswordAuthenticationConverter(),
                new OAuth2RefreshTokenAuthenticationConverter(),
                new OAuth2ClientCredentialsAuthenticationConverter(),
                new OAuth2AuthorizationCodeAuthenticationConverter(),
                new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
    }

    public void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        // 表单认证使用自定义 provider，处理 UsernamePasswordAuthenticationToken
//        http.authenticationProvider(new ZeusUserDetailsAuthenticationProvider());

        // 添加 oauth2 的密码模式，处理 OAuth2UsernamePasswordAuthenticationToken
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
        OAuth2UsernamePasswordAuthenticationProvider customProvider = new OAuth2UsernamePasswordAuthenticationProvider(
                authorizationService, oAuth2TokenGenerator(), authenticationManager);
        http.authenticationProvider(customProvider);
    }

    /**
     * 注入自定义的 opaqueToken 生成器，用于生成较短的 access_token 和 refresh_token
     */
    @Bean
    public OAuth2TokenGenerator<?> oAuth2TokenGenerator() {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource(generateRsaKey()));
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);

        UUIDOAuth2AccessTokenGenerator uuidOAuth2AccessTokenGenerator = new UUIDOAuth2AccessTokenGenerator();
        uuidOAuth2AccessTokenGenerator.setAccessTokenCustomizer(new ZeusOAuth2TokenCustomizer());

        UUIDOAuth2RefreshTokenGenerator uuidOAuth2RefreshTokenGenerator = new UUIDOAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(uuidOAuth2AccessTokenGenerator, uuidOAuth2RefreshTokenGenerator, jwtGenerator);
    }

    /**
     * jwk 配置
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}

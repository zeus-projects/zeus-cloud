package tech.alexchen.zeus.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.annotation.Resource;

/**
 * 授权服务器配置
 *
 * @author alexchen
 * @date 2023/2/16
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) { //配置令牌的访问端点和令牌服务
        //认证管理器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //基于内存便于测试
        clients.inMemory() // 使用in-memory存储
                .withClient("test")// client_id
                // 未加密
                //.secret("secret")
                // 加密
                .secret(passwordEncoder.encode("secret"))
                //资源列表
                //.resourceIds("res1")
                // 该client允许的授权类型 authorization_code, password, refresh_token, implicit, client_credentials
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token", "client_credentials")
                // 允许的授权范围
                .scopes("ROLE_ADMIN", "ROLE_USER")
                //false 跳转到授权页面
                //.autoApprove(false)
                // 验证回调地址
                .redirectUris("http://www.baidu.com");
    }


}

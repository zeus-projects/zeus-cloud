package tech.alexchen.zeus.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity安全框架配置：
 *
 * @author alexchen
 * @EnableWebSecurity 开启 Spring Security 的功能
 * @date 2023/2/15
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCrypt 加密密码
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器：自定义认证策略
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(    auth) throws Exception {
//        String adminAuthorities = "ROLE_DEPT, ROLE_USER, QUERY, SAVE, UPDATE, DELETE";
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("USER", "ADMIN")
//                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(adminAuthorities))
//                .and()
//                .withUser("alex")
//                .password(passwordEncoder().encode("123456"))
//                .roles("USER")
//                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEPT, QUERY, SAVE"));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 开启默认表单
//        http.formLogin().successHandler(authenticationSuccessHandler());
//        http.logout().deleteCookies();
//    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new LoginAuthenticationSuccessHandler();
//    }
}

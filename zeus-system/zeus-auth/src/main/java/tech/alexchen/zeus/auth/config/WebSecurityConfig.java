package tech.alexchen.zeus.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tech.alexchen.zeus.auth.handler.LoginAuthenticationSuccessHandler;

/**
 * SpringSecurity安全框架配置：
 * <p>
 * 1. @EnableWebSecurity 开启 Spring Security 的功能
 * 2. @EnableGlobalMethodSecurity 的 prePostEnabled 属性决定 Spring Security 在接口前注解是否可用 @PreAuthorize,
 *
 * @author alexchen
 * @PostAuthorize 等注解, 设置为 true,会拦截加了这些注解的接口
 *
 * </p>
 * @date 2023/2/15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String adminAuthorities = "ROLE_DEPT, ROLE_USER, QUERY, SAVE, UPDATE, DELETE";
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("USER", "ADMIN")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(adminAuthorities))
                .and()
                .withUser("alex")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEPT, QUERY, SAVE"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不拦截静态资源
//        web.ignoring().antMatchers("/favicon.ico", "/static/css/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/test/dept").hasRole("DEPT")
                .antMatchers("/test/dept/save").hasAuthority("SAVE")
                .antMatchers("/test/dept/update").hasAuthority("UPDATE");
        // 开启默认表单
        http.formLogin().successHandler(authenticationSuccessHandler());
        http.logout().deleteCookies();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginAuthenticationSuccessHandler();
    }
}

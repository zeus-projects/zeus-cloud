package tech.alexchen.zeus.auth.support.core;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * Form 表单登录配置
 *
 * @author alexchen
 */
public final class FormIdentityLoginConfigurer extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.formLogin(formLogin -> formLogin
                                .loginPage("/token/login")
                                .loginProcessingUrl("/token/form")
//                        .failureHandler(new FormAuthenticationFailureHandler())
                )
                .logout(logout -> logout
//                        .logoutSuccessHandler(new SsoLogoutSuccessHandler())
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                )
                .csrf()
                .disable();
    }

}
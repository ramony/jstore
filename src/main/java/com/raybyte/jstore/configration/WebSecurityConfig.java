package com.raybyte.jstore.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final static String ACCOUNT_CLIENT_AUTHORITY = "admin";

    //配置basicauth账号密码
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User
                .withUsername("user")
                //  .password("123456")
              .password(encoder().encode("123456"))
                .roles("ADMIN")
                .build());
        return users;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    //配置不同接口访问权限
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests(auth -> {
                    auth.antMatchers("/ping", "/login", "/static/login.html","/static/401.json").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(conf -> {
                    conf.loginPage("/static/401.json");  // 自定义登录页
                    conf.loginProcessingUrl("/login");  // 用户点击登录后，提交到 user/login 接口进行验证。
                })
//                .exceptionHandling(e -> {
//                    e.authenticationEntryPoint(authenticationEntryPoint());
//                })
                .csrf().disable()
                .build();

//        return http
//                .csrf().disable()
//                .build();
    }

//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        entryPoint.setRealmName("admin realm");
//        return entryPoint;
//    }
}

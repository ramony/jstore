package com.raybyte.jstore.configration;

import com.raybyte.jstore.auth.JwtTokenAuthenticationFilter;
import com.raybyte.jstore.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //配置不同接口访问权限
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> {
                    auth
                            .antMatchers("/ping", "/auth/login").permitAll()
                            .antMatchers(HttpMethod.GET, "/test/admin").hasRole("ADMIN")
                            .antMatchers(HttpMethod.GET, "/test/user").hasRole("USER")
                            .anyRequest().authenticated();
                })
                .exceptionHandling(e -> {
                    JwtTokenAuthenticationFilter customFilter = new JwtTokenAuthenticationFilter(jwtTokenProvider);
                    e.authenticationEntryPoint(new InvalidAuthenticationEntryPoint())
                            .and()
                            .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
                    ;
                })
                .build();
    }
}

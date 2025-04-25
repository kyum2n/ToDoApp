package com.example.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/main", "/todos/join", "/todos/login", "/css/**", "/js/**", "/images/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable()) // 직접 로그인 처리
                .csrf(csrf -> csrf.disable()) // CSRF 보호 끄기 (form post로 로그인할 수 있게)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/todos/login")
                        .defaultSuccessUrl("/main", true));
        return http.build();
    }
}

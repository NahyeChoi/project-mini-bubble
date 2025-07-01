package com.nahye.mini_bubble.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (Postman 요청 가능하게)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup").permitAll()  // ✅ 회원가입 허용
                        .anyRequest().authenticated()           // 나머지는 인증 필요
                )
                .formLogin(Customizer.withDefaults());       // (지금은 기본 로그인 폼 사용)

        return http.build();
    }
}

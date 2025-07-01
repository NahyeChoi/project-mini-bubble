package com.nahye.mini_bubble.security.config;

import com.nahye.mini_bubble.repository.UserRepository;
import com.nahye.mini_bubble.security.filter.JwtAuthFilter;
import com.nahye.mini_bubble.security.util.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (Postman 요청 가능하게)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/login").permitAll()  // 회원가입, 로그인 허용
                        .anyRequest().authenticated()           // 나머지는 인증 필요
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 비활성화
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider, userRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

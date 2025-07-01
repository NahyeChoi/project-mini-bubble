package com.nahye.mini_bubble.service;

import com.nahye.mini_bubble.dto.UserLoginDTO;
import com.nahye.mini_bubble.dto.UserSignupDTO;
import com.nahye.mini_bubble.entity.Role;
import com.nahye.mini_bubble.entity.User;
import com.nahye.mini_bubble.repository.UserRepository;
import com.nahye.mini_bubble.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    public void signup(UserSignupDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.FAN)
                .nickname(request.getName())
                .statusMessage("상태 메시지를 입력해주세요.")
                .profileImageUrl("/default-profile.png") // 실제 기본 이미지 경로 설정
                .build();

        userRepository.save(user);
    }

    // 로그인
    public String login(UserLoginDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }

}

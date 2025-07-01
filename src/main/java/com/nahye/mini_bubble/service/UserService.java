package com.nahye.mini_bubble.service;

import com.nahye.mini_bubble.dto.UserSignupDTO;
import com.nahye.mini_bubble.entity.Role;
import com.nahye.mini_bubble.entity.User;
import com.nahye.mini_bubble.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}

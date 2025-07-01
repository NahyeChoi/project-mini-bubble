package com.nahye.mini_bubble.controller;

import com.nahye.mini_bubble.dto.UserLoginDTO;
import com.nahye.mini_bubble.dto.UserSignupDTO;
import com.nahye.mini_bubble.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid UserSignupDTO request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    // 테스트용 protected api
    @GetMapping("/protected-api")
    public ResponseEntity<String> protectedTest() {
        return ResponseEntity.ok("인증된 사용자만 접근할 수 있는 API입니다.");
    }

}

package com.nahye.mini_bubble.controller;

import com.nahye.mini_bubble.dto.UserSignupDTO;
import com.nahye.mini_bubble.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

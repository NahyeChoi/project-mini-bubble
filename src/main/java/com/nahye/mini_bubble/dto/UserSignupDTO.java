package com.nahye.mini_bubble.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignupDTO {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank
    private String email;

    @NotBlank(message = "비밀번호(password)는 필수 입력 값입니다.")
    private String password;
}

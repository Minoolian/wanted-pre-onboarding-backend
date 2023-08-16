package com.wanted.preonboarding.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserPostDto {

    @Email(message = "@를 포함한 Email 양식이어야 합니다.")
    private String email;

    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;

    private String username;
}

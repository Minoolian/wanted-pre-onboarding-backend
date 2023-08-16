package com.wanted.preonboarding.user.dto;

import com.wanted.preonboarding.user.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserGetDto {

    private String email;

    private String username;

    private RoleType role;
}

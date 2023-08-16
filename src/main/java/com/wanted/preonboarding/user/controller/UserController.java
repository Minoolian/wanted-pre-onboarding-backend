package com.wanted.preonboarding.user.controller;

import com.wanted.preonboarding.user.dto.UserGetDto;
import com.wanted.preonboarding.user.dto.UserPostDto;
import com.wanted.preonboarding.user.entity.User;
import com.wanted.preonboarding.user.mapper.UserMapper;
import com.wanted.preonboarding.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserGetDto> signUp(@RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);
        User createdUser = userService.signUp(user);
        return new ResponseEntity<>(userMapper.userToUserGetDto(createdUser), HttpStatus.CREATED);
    }
}

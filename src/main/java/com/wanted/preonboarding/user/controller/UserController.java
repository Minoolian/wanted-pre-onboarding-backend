package com.wanted.preonboarding.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wanted.preonboarding.user.auth.PrincipalDetails;
import com.wanted.preonboarding.user.config.JwtConfig;
import com.wanted.preonboarding.user.dto.UserGetDto;
import com.wanted.preonboarding.user.dto.UserLoginDto;
import com.wanted.preonboarding.user.dto.UserPostDto;
import com.wanted.preonboarding.user.entity.User;
import com.wanted.preonboarding.user.mapper.UserMapper;
import com.wanted.preonboarding.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtConfig jwtConfig;

    @PostMapping("/signup")
    public ResponseEntity<UserGetDto> signUp(@Valid @RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);
        User createdUser = userService.signUp(user);
        return new ResponseEntity<>(userMapper.userToUserGetDto(createdUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = generateJwt((PrincipalDetails) authentication.getPrincipal());

        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + jwt);

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    private String generateJwt(PrincipalDetails principalDetails) {
        return  JWT.create()
                .withSubject("JWT")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 100)))
                .withClaim("id", principalDetails.getUser().getUserId())
                .withClaim("email", principalDetails.getUser().getEmail())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(jwtConfig.getSecret()));
    }
}

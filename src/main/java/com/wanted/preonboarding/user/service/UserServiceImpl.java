package com.wanted.preonboarding.user.service;

import com.wanted.preonboarding.user.entity.RoleType;
import com.wanted.preonboarding.user.entity.User;
import com.wanted.preonboarding.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(User user) {
        user.setRole(RoleType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

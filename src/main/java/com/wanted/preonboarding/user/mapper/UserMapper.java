package com.wanted.preonboarding.user.mapper;

import com.wanted.preonboarding.user.dto.UserGetDto;
import com.wanted.preonboarding.user.dto.UserPostDto;
import com.wanted.preonboarding.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetDto userToUserGetDto(User user);

    User userPostDtoToUser(UserPostDto userPostDto);
}

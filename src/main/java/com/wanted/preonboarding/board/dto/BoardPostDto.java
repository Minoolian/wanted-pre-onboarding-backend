package com.wanted.preonboarding.board.dto;

import com.wanted.preonboarding.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardPostDto {

    private Long userId;

    private String title;

    private String content;

    public User getUser() {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

}

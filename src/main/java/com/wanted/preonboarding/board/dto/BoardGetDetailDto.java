package com.wanted.preonboarding.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardGetDetailDto {

    private Long userId;

    private Long boardId;

    private String title;

    private String content;
}

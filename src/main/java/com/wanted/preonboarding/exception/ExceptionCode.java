package com.wanted.preonboarding.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCode {
    BOARD_NOT_FOUND(404, "게시글이 존재하지 않습니다.");

    @Getter
    final int status;

    @Getter
    final String message;
}

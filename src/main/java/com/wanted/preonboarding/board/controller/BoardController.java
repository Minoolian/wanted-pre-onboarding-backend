package com.wanted.preonboarding.board.controller;

import com.wanted.preonboarding.board.dto.BoardPostDto;
import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.mapper.BoardMapper;
import com.wanted.preonboarding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final BoardMapper boardMapper;

    @PostMapping
    public ResponseEntity<Long> postBoard(@RequestBody BoardPostDto boardPostDto) {
        Board board = boardMapper.boardPostDtoToBoard(boardPostDto);
        Board createdBoard = boardService.createBoard(board);
        return new ResponseEntity<>(createdBoard.getBoardId(), HttpStatus.CREATED);
    }

}

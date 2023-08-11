package com.wanted.preonboarding.board.controller;

import com.wanted.preonboarding.board.dto.BoardGetDetailDto;
import com.wanted.preonboarding.board.dto.BoardGetDto;
import com.wanted.preonboarding.board.dto.BoardPatchDto;
import com.wanted.preonboarding.board.dto.BoardPostDto;
import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.mapper.BoardMapper;
import com.wanted.preonboarding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardGetDetailDto> getBoard(@PathVariable Long boardId) {
        Board board = boardService.readBoard(boardId);
        return new ResponseEntity<>(boardMapper.boardToBoardGetDetailDto(board), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardGetDto>> getBoards(int page, int size) {
        Page<Board> boards = boardService.readBoards(page - 1, size);
        return new ResponseEntity<>(boardMapper.boardsToBoardGetDTOs(boards.getContent()), HttpStatus.OK);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<BoardGetDetailDto> patchBoard(@PathVariable Long boardId, @RequestBody BoardPatchDto boardPatchDto) {
        Board board = boardService.updateBoard(boardId, boardPatchDto);
        return new ResponseEntity<>(boardMapper.boardToBoardGetDetailDto(board), HttpStatus.OK);
    }

}

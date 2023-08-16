package com.wanted.preonboarding.board.service;

import com.wanted.preonboarding.board.dto.BoardPatchDto;
import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.repository.BoardRepostitory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepostitory boardRepostitory;

    @Override
    public Board createBoard(Board board) {
        return boardRepostitory.save(board);
    }

    @Override
    public Board readBoard(Long boardId) {
        return verifyBoard(boardId);
    }

    private Board verifyBoard(Long boardId) {
        return boardRepostitory.findById(boardId).orElseThrow(() -> new NullPointerException());
    }

    @Override
    public Page<Board> readBoards(int page, int size) {
        return boardRepostitory.findAll(PageRequest.of(page, size, Sort.by("boardId").descending()));
    }

    @Override
    public Board updateBoard(Long boardId, BoardPatchDto newBoard, Long userId) {
        Board currentBoard = verifyBoard(boardId);
        if (currentBoard.getUserId() != userId) throw new NullPointerException();
        return currentBoard.correctBoard(newBoard);
    }

    @Override
    public void deleteBoard(Long boardId, Long userId) {
        Board board = verifyBoard(boardId);
        if (board.getUserId() != userId) throw new NullPointerException();
        boardRepostitory.delete(board);
    }
}

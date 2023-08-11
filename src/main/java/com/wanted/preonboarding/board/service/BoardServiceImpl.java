package com.wanted.preonboarding.board.service;

import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.repository.BoardRepostitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
    public Board updateBoard(Long boardId, Board newBoard) {
        Board currentBoard = verifyBoard(boardId);
        return currentBoard.correctBoard(newBoard);
    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = verifyBoard(boardId);
        boardRepostitory.delete(board);
    }
}

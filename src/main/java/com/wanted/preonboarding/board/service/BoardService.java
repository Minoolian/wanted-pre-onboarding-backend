package com.wanted.preonboarding.board.service;

import com.wanted.preonboarding.board.dto.BoardPatchDto;
import com.wanted.preonboarding.board.entity.Board;
import org.springframework.data.domain.Page;

public interface BoardService {

    public Board createBoard(Board board);

    public Board readBoard(Long boardId);

    public Page<Board> readBoards(int page, int size);

    public Board updateBoard(Long boardId, BoardPatchDto board, Long userId);

    public void deleteBoard(Long boardId, Long userId);

}

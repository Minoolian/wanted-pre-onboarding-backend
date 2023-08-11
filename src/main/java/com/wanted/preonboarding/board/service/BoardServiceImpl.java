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


}

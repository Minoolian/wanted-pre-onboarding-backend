package com.wanted.preonboarding.board.repository;

import com.wanted.preonboarding.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepostitory extends JpaRepository<Board, Long> {
}

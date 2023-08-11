package com.wanted.preonboarding.board.entity;

import com.wanted.preonboarding.audit.Auditable;
import com.wanted.preonboarding.board.dto.BoardPatchDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Board correctBoard(BoardPatchDto newBoard) {
        this.title = newBoard.getTitle();
        this.content = newBoard.getContent();

        return this;
    }
}

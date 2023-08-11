package com.wanted.preonboarding.board.entity;

import com.wanted.preonboarding.audit.Auditable;
import jakarta.persistence.*;

@Entity
public class Board extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Board correctBoard(Board newBoard) {
        this.title = newBoard.title;
        this.content = newBoard.content;

        return this;
    }
}

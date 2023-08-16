package com.wanted.preonboarding.board.service;

import com.wanted.preonboarding.board.dto.BoardPatchDto;
import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.repository.BoardRepostitory;
import com.wanted.preonboarding.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @Mock
    private BoardRepostitory boardRepostitory;

    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    @DisplayName("새로운 게시글을 생성")
    void createBoard() {
        //given
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        given(boardRepostitory.save(board)).willReturn(board);

        //when
        Board createdBoard = boardService.createBoard(board);

        //then
        assertThat(createdBoard).isEqualTo(board);
        verify(boardRepostitory, times(1)).save(any(Board.class));
    }

    @Test
    @DisplayName("특정 게시글을 조회")
    void readBoard() {
        //given
        Long boardId = 1L;
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        given(boardRepostitory.findById(boardId)).willReturn(Optional.of(board));

        //when
        Board readBoard = boardService.readBoard(boardId);

        //then
        assertThat(readBoard).isEqualTo(board);
        verify(boardRepostitory, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("게시글 목록을 조회")
    void readBoards() {
        //given
        int page = 0;
        int size = 10;
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        Page<Board> boards = new PageImpl<>(List.of(board));
        given(boardRepostitory.findAll(PageRequest.of(page, size, Sort.by("boardId").descending()))).willReturn(boards);

        //when
        Page<Board> readBoards = boardService.readBoards(page, size);

        //then
        assertThat(readBoards).isEqualTo(boards);
        verify(boardRepostitory, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("특정 게시글을 수정")
    void updateBoard() {
        //given
        Long boardId = 1L;
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        BoardPatchDto newBoard = new BoardPatchDto("New Title", "New Content");
        given(boardRepostitory.findById(boardId)).willReturn(Optional.of(board));

        //when
        Board updateBoard = boardService.updateBoard(boardId, newBoard);

        //then
        assertThat(updateBoard.getTitle()).isEqualTo(newBoard.getTitle());
        assertThat(updateBoard.getContent()).isEqualTo(newBoard.getContent());
        verify(boardRepostitory, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("특정 게시글을 삭제")
    void deleteBoard() {
        //given
        Long boardId = 1L;
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        given(boardRepostitory.findById(boardId)).willReturn(Optional.of(board));

        //when
        boardService.deleteBoard(boardId);

        //then
        verify(boardRepostitory, times(1)).findById(anyLong());
        verify(boardRepostitory, times(1)).delete(any(Board.class));
    }
}
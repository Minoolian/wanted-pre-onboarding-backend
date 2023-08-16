package com.wanted.preonboarding.board.controller;

import com.google.gson.Gson;
import com.wanted.preonboarding.board.dto.BoardGetDetailDto;
import com.wanted.preonboarding.board.dto.BoardGetDto;
import com.wanted.preonboarding.board.dto.BoardPatchDto;
import com.wanted.preonboarding.board.dto.BoardPostDto;
import com.wanted.preonboarding.board.entity.Board;
import com.wanted.preonboarding.board.mapper.BoardMapper;
import com.wanted.preonboarding.board.service.BoardService;
import com.wanted.preonboarding.config.WithMockCustomUser;
import com.wanted.preonboarding.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
@WithMockCustomUser
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardMapper boardMapper;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("새로운 게시글을 생성 API")
    void postBoard() throws Exception {
        //given
        BoardPostDto boardPostDto = new BoardPostDto(1L, "Test Title", "Test Content");
        String json = gson.toJson(boardPostDto);
        Long boardId = 1L;

        given(boardMapper.boardPostDtoToBoard(any(BoardPostDto.class))).willReturn(new Board());
        given(boardService.createBoard(any(Board.class))).willReturn(new Board(1L, boardPostDto.getTitle(), boardPostDto.getContent(), new User()));

        //when
        ResultActions perform = mockMvc.perform(
                post("/board")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        //then
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(boardId));
    }

    @Test
    @DisplayName("특정 게시글을 조회 API")
    void getBoard() throws Exception {
        //given
        Long boardId = 1L;
        BoardGetDetailDto boardGetDetailDto = new BoardGetDetailDto(1L, 1L, "Test Title", "Test Content");
        given(boardService.readBoard(anyLong())).willReturn(new Board());
        given(boardMapper.boardToBoardGetDetailDto(any(Board.class))).willReturn(boardGetDetailDto);

        //when
        ResultActions perform = mockMvc.perform(
                get("/board/1")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(boardGetDetailDto.getTitle()))
                .andExpect(jsonPath("content").value(boardGetDetailDto.getContent()));
    }

    @Test
    @DisplayName("게시글 목록을 조회 API")
    void getBoards() throws Exception {
        //given
        int page = 1;
        int size = 10;
        Board board = new Board(1L, "Test Title", "Test Content", new User());
        BoardGetDto boardGetDto = new BoardGetDto(1L, 1L, "Test Title");
        List<BoardGetDto> boardGetDtos = List.of(boardGetDto);
        Page<Board> boards = new PageImpl<>(List.of(board));

        given(boardService.readBoards(anyInt(), anyInt())).willReturn(boards);
        given(boardMapper.boardsToBoardGetDTOs(anyList())).willReturn(boardGetDtos);

        //when
        ResultActions perform = mockMvc.perform(
                get("/board")
                        .param("page", Integer.toString(page))
                        .param("size", Integer.toString(size))
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("특정 게시글을 수정 API")
    void patchBoard() throws Exception {
        //given
        BoardPatchDto boardPatchDto = new BoardPatchDto("New Title", "New Content");
        BoardGetDetailDto boardGetDetailDto = new BoardGetDetailDto(1L, 1L, "New Title", "New Content");
        String json = gson.toJson(boardPatchDto);
        Long boardId = 1L;

        given(boardService.updateBoard(anyLong(), any(BoardPatchDto.class), anyLong())).willReturn(new Board());
        given(boardMapper.boardToBoardGetDetailDto(any(Board.class))).willReturn(boardGetDetailDto);

        //when
        ResultActions perform = mockMvc.perform(
                patch("/board/1")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(boardPatchDto.getTitle()))
                .andExpect(jsonPath("$.content").value(boardPatchDto.getContent()));
    }

    @Test
    @DisplayName("특정 게시글을 삭제 API")
    void deleteBoard() throws Exception {
        //given
        Long boardId = 1L;
        Long userId = 1L;

        doNothing().when(boardService).deleteBoard(boardId, userId);

        //when
        ResultActions perform = mockMvc.perform(
                delete("/board/1")
                        .with(csrf())
        );

        //then
        perform.andExpect(status().isNoContent());
    }
}
package com.wanted.preonboarding.board.mapper;

import com.wanted.preonboarding.board.dto.BoardGetDetailDto;
import com.wanted.preonboarding.board.dto.BoardGetDto;
import com.wanted.preonboarding.board.dto.BoardPostDto;
import com.wanted.preonboarding.board.entity.Board;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPostDtoToBoard(BoardPostDto boardPostDto);

    BoardGetDetailDto boardToBoardGetDetailDto(Board board);

    BoardGetDto boardToBoardGetDto(Board board);

    List<BoardGetDto> boardsToBoardGetDTOs(List<Board> content);
}

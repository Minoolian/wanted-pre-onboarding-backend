package com.wanted.preonboarding.board.mapper;

import com.wanted.preonboarding.board.dto.BoardPostDto;
import com.wanted.preonboarding.board.entity.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPostDtoToBoard(BoardPostDto boardPostDto);
}

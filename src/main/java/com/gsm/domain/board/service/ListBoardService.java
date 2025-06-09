package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.enums.BoardType;
import com.gsm.domain.board.presentation.dto.response.BoardResponse;
import com.gsm.domain.board.presentation.dto.response.ListBoardResponse;
import com.gsm.domain.board.repository.BoardRepository;
import com.gsm.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ReadOnlyService
public class ListBoardService {

    private final BoardRepository boardRepository;

    public ListBoardResponse execute(BoardType boardType) {

        List<Board> boards = boardRepository.findByBoardType(boardType);

        return ListBoardResponse.builder()
                .boardList(
                        boards.stream()
                                .map(BoardResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

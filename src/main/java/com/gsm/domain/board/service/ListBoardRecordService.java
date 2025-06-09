package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.entity.BoardRecord;
import com.gsm.domain.board.presentation.dto.response.BoardRecordResponse;
import com.gsm.domain.board.presentation.dto.response.ListBoardRecordResponse;
import com.gsm.domain.board.repository.BoardRecordRepository;
import com.gsm.global.annotation.ReadOnlyService;
import com.gsm.global.utils.BoardUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ReadOnlyService
public class ListBoardRecordService {

    private final BoardRecordRepository boardRecordRepository;

    private final BoardUtil boardUtil;

    public ListBoardRecordResponse execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        List<BoardRecord> boardRecords = boardRecordRepository.findByBoard(board);

        return ListBoardRecordResponse.builder()
                .boardRecordList(
                        boardRecords.stream()
                                .map(BoardRecordResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}


package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.BoardRecord;
import com.gsm.domain.board.exception.BoardRecordNotFoundException;
import com.gsm.domain.board.presentation.dto.response.DetailBoardResponse;
import com.gsm.domain.board.repository.BoardRecordRepository;
import com.gsm.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class GetBoardRecordDetailService {

    private final BoardRecordRepository boardRecordRepository;

    public DetailBoardResponse execute(Long id) {

        BoardRecord boardRecord = boardRecordRepository.findById(id)
                .orElseThrow(BoardRecordNotFoundException::new);

        return DetailBoardResponse.builder()
                .id(boardRecord.getId())
                .title(boardRecord.getTitle())
                .content(boardRecord.getContent())
                .createdDate(boardRecord.getCreatedDate())
                .build();
    }
}
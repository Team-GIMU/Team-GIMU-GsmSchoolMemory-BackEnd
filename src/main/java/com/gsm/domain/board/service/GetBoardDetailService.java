package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.presentation.dto.response.DetailBoardResponse;
import com.gsm.global.annotation.ReadOnlyService;
import com.gsm.global.utils.BoardUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class GetBoardDetailService {

    private final BoardUtil boardUtil;

    public DetailBoardResponse execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        return DetailBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .editedDate(board.getEditedDate())
                .build();
    }
}

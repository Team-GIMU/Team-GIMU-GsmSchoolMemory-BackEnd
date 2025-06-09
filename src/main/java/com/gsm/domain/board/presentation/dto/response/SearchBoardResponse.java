package com.gsm.domain.board.presentation.dto.response;

import com.gsm.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchBoardResponse {

    private Long id;

    private String title;

    public static SearchBoardResponse toResponse(Board board) {

        return SearchBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .build();
    }
}
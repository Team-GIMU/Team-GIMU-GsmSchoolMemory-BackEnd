package com.gsm.domain.board.presentation.dto.response;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.enums.BoardDetailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private Long id;

    private String title;

    private BoardDetailType boardDetailType;

    public static BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .boardDetailType(board.getBoardDetailType())
                .build();
    }
}

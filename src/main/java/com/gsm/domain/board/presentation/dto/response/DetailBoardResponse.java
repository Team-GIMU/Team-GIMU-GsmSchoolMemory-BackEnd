package com.gsm.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DetailBoardResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime editedDate;
}

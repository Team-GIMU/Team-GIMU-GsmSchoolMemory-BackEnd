package com.gsm.domain.board.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListBoardResponse {

    private List<BoardResponse> boardList;
}

package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.presentation.dto.request.SearchBoardTitleRequest;
import com.gsm.domain.board.presentation.dto.response.ListSearchBoardResponse;
import com.gsm.domain.board.presentation.dto.response.SearchBoardResponse;
import com.gsm.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ListSearchBoardTitleService {

    private final BoardRepository boardRepository;

    public ListSearchBoardResponse execute(SearchBoardTitleRequest searchBoardTitleRequest) {

        List<Board> titles = boardRepository.findByTitle(searchBoardTitleRequest.getTitle());

        return ListSearchBoardResponse.builder()
                .boardTitleList(
                        titles.stream()
                                .map(SearchBoardResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

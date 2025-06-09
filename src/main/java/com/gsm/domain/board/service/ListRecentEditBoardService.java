package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.presentation.dto.response.ListResentBoardResponse;
import com.gsm.domain.board.presentation.dto.response.ResentBoardResponse;
import com.gsm.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ListRecentEditBoardService {

    private final BoardRepository boardRepository;

    public ListResentBoardResponse execute() {

        List<Board> titles = boardRepository.findRecentlyModifiedBoards();

        return ListResentBoardResponse.builder()
                .boardTitleList(
                        titles.stream()
                                .map(ResentBoardResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

package com.gsm.global.utils;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.exception.BoardNotFoundException;
import com.gsm.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardUtil {

    private final BoardRepository boardRepository;

    public Board findBoardById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
    }
}
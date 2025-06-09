package com.gsm.domain.board.repository;

import com.gsm.domain.board.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findByTitle(String title);

    List<Board> findRecentlyModifiedBoards();
}

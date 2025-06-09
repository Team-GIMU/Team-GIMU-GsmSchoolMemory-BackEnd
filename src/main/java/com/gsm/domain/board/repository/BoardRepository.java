package com.gsm.domain.board.repository;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.enums.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    List<Board> findByBoardType(BoardType boardType);

    boolean existsByTitle(String title);
}


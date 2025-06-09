package com.gsm.domain.board.repository;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.entity.BoardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRecordRepository extends JpaRepository<BoardRecord, Long> {

    List<BoardRecord> findByBoard(Board board);

    void deleteAllByBoard(Board board);
}

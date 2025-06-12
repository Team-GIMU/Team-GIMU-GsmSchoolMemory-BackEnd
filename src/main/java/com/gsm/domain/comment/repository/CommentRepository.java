package com.gsm.domain.comment.repository;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard(Board board);
}

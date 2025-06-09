package com.gsm.domain.board.repository.impl;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.entity.QBoard;
import com.gsm.domain.board.repository.BoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findByTitle(String title) {

        return jpaQueryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.title.contains(title))
                .orderBy(QBoard.board.title.asc())
                .limit(12)
                .fetch();
    }

    @Override
    public List<Board> findRecentlyModifiedBoards() {

        return jpaQueryFactory
                .selectFrom(QBoard.board)
                .orderBy(QBoard.board.editedDate.desc())
                .limit(12)
                .fetch();
    }
}

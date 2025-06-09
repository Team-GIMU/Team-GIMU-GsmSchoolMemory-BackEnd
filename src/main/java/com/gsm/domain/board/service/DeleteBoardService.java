package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.exception.BoardAuthorMismatchException;
import com.gsm.domain.board.repository.BoardRecordRepository;
import com.gsm.domain.board.repository.BoardRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.enums.Role;
import com.gsm.domain.user.util.UserUtil;
import com.gsm.global.annotation.RollbackService;
import com.gsm.global.utils.BoardUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RollbackService
public class DeleteBoardService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    private final UserUtil userUtil;

    private final BoardUtil boardUtil;

    public void execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        User user = userUtil.currentUser();

        if(!(board.getUser() == user) && user.getRole().equals(Role.ROLE_STUDENT)) {

            throw new BoardAuthorMismatchException();
        }

        boardRecordRepository.deleteAllByBoard(board);

        boardRepository.delete(board);
    }
}

package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.entity.BoardRecord;
import com.gsm.domain.board.exception.BoardNotChangeException;
import com.gsm.domain.board.exception.ExistTitleException;
import com.gsm.domain.board.presentation.dto.request.EditBoardRequest;
import com.gsm.domain.board.repository.BoardRecordRepository;
import com.gsm.domain.board.repository.BoardRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.util.UserUtil;
import com.gsm.global.annotation.RollbackService;
import com.gsm.global.utils.BoardUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RollbackService
public class EditBoardService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    private final BoardUtil boardUtil;

    private final UserUtil userUtil;

    public void execute(Long id, EditBoardRequest editBoardRequest) {

        Board board = boardUtil.findBoardById(id);

        User user = userUtil.currentUser();

        if (board.getTitle().equals(editBoardRequest.getTitle()) && board.getContent().equals(editBoardRequest.getContent())) {
            throw new BoardNotChangeException();
        } else if (!board.getTitle().equals(editBoardRequest.getTitle()) && boardRepository.existsByTitle(editBoardRequest.getTitle())) {
            throw new ExistTitleException();
        }

        BoardRecord boardRecord = BoardRecord.builder()
                .title(editBoardRequest.getTitle())
                .content(editBoardRequest.getContent())
                .name(user.getName())
                .boardType(board.getBoardType())
                .createdDate(board.getEditedDate())
                .board(board)
                .build();

        boardRecordRepository.save(boardRecord);

        board.update(editBoardRequest);
    }

}

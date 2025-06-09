package com.gsm.domain.board.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.entity.BoardRecord;
import com.gsm.domain.board.enums.BoardDetailType;
import com.gsm.domain.board.enums.BoardType;
import com.gsm.domain.board.exception.ExistTitleException;
import com.gsm.domain.board.presentation.dto.request.CreateBoardRequest;
import com.gsm.domain.board.repository.BoardRecordRepository;
import com.gsm.domain.board.repository.BoardRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.util.UserUtil;
import com.gsm.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RollbackService
public class CreateBoardService {

    private final UserUtil userUtil;

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    public void execute(CreateBoardRequest createBoardRequest) {

        User user = userUtil.currentUser();

        if(boardRepository.existsByTitle(createBoardRequest.getTitle())) {
            throw new ExistTitleException();
        }

        Board board = Board.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .name(user.getName())
                .boardType(BoardType.from(createBoardRequest.getBoardType()))
                .boardDetailType(BoardDetailType.from(createBoardRequest.getBoardDetailType()))
                .user(user)
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();

        BoardRecord boardRecord = BoardRecord.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .name(board.getName())
                .boardType(board.getBoardType())
                .createdDate(board.getCreatedDate())
                .board(board)
                .build();

        boardRepository.save(board);

        boardRecordRepository.save(boardRecord);
    }
}

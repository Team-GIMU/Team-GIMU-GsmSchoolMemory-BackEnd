package com.gsm.domain.board.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BoardNotChangeException extends CustomException {

    public BoardNotChangeException() {
        super(ErrorCode.BOARD_NOT_CHANGE);
    }
}


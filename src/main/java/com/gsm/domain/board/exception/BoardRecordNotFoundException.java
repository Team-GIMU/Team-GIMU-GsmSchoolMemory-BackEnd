package com.gsm.domain.board.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BoardRecordNotFoundException extends CustomException {
    public BoardRecordNotFoundException() {
        super(ErrorCode.BOARD_RECORD_NOT_FOUND);
    }
}

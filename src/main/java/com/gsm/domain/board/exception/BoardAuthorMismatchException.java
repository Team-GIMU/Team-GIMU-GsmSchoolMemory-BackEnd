package com.gsm.domain.board.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BoardAuthorMismatchException extends CustomException {
    public BoardAuthorMismatchException() {
        super(ErrorCode.MISMATCH_BOARD_AUTHOR);
    }
}

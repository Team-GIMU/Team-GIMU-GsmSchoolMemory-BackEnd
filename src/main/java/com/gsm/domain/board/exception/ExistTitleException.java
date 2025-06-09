package com.gsm.domain.board.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class ExistTitleException extends CustomException {

    public ExistTitleException() {
        super(ErrorCode.ALREADY_EXIST_TITLE);
    }
}

package com.gsm.domain.auth.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class PasswordNotMatchException extends CustomException {
    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}


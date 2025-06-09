package com.gsm.domain.auth.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class TokenNotFoundException extends CustomException {
    public TokenNotFoundException() {
        super(ErrorCode.TOKEN_NOT_FOUND);
    }
}

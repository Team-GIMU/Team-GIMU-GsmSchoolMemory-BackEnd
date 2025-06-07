package com.gsm.global.security.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class TokenNotValidException extends CustomException {
    public TokenNotValidException(){
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
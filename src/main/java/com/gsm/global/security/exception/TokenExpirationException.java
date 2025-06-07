package com.gsm.global.security.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class TokenExpirationException extends CustomException {
    public TokenExpirationException(){
        super(ErrorCode.TOKEN_IS_EXPIRATION);
    }
}

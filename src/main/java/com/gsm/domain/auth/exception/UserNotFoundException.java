package com.gsm.domain.auth.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}

package com.gsm.domain.inquiry.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EmailSendFailedException extends CustomException {
    public EmailSendFailedException() {
        super(ErrorCode.EMAIL_SEND_FAIL);
    }
}

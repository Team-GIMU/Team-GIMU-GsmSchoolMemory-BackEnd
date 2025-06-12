package com.gsm.domain.inquiry.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class InquiryNotFoundException extends CustomException {
    public InquiryNotFoundException() {
        super(ErrorCode.INQUIRY_NOT_FOUND);
    }
}

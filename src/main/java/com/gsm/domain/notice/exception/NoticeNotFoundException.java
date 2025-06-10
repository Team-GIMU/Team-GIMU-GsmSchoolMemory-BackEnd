package com.gsm.domain.notice.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class NoticeNotFoundException extends CustomException {
    public NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}

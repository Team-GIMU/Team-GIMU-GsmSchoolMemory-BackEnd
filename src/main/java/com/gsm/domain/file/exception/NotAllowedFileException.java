package com.gsm.domain.file.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotAllowedFileException extends CustomException {
    public NotAllowedFileException() {
        super(ErrorCode.NOT_ALLOWED_FILE);
    }
}

package com.gsm.domain.file.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class FileUploadFailedException extends CustomException {
    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}

package com.gsm.domain.comment.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}

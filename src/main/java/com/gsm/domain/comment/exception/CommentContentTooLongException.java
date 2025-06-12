package com.gsm.domain.comment.exception;

import com.gsm.global.error.CustomException;
import com.gsm.global.error.ErrorCode;

public class CommentContentTooLongException extends CustomException {
    public CommentContentTooLongException() {
        super(ErrorCode.COMMENT_CONTENT_TOO_LONG);
    }
}

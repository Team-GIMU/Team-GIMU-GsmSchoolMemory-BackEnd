package com.gsm.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // SERVER ERROR
    EMAIL_SEND_FAIL(500,"메일 발송에 실패했습니다"),

    // TOKEN
    TOKEN_NOT_VALID(401,"TOKEN_NOT_VALID"),
    TOKEN_IS_EXPIRATION(401,"TOKEN_IS_EXPIRATION"),
    TOKEN_NOT_FOUND(404, "TOKEN_NOT_FOUND"),

    // USER
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    ALREADY_EXIST_USERNAME(409, "ALREADY_EXIST_USERNAME"),

    // LOGIN
    PASSWORD_NOT_MATCH(401, "PASSWORD_NOT_MATCH"),

    // BOARD
    ALREADY_EXIST_TITLE(409,"이미 존재하는 제목입니다."),
    BOARD_NOT_FOUND( 404,"게시글을 찾을 수 없습니다."),
    BOARD_NOT_CHANGE(400,"달라진 수정사항이 없습니다."),
    MISMATCH_BOARD_AUTHOR(403,"내가 작성한 글이 아닙니다."),

    //BOARD RECORD
    BOARD_RECORD_NOT_FOUND(404,"게시글의 기록을 찾을 수 없습니다."),

    // NOTICE
    NOTICE_NOT_FOUND(404,"공지글을 찾을 수 없습니다"),

    //INQUIRY
    INQUIRY_NOT_FOUND(404,"문의 사항을 찾을 수 없습니다.");

    private final int status;
    private final String message;
}

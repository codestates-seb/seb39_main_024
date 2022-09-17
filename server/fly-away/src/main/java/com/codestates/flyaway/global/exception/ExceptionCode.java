package com.codestates.flyaway.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    EMAIL_ALREADY_EXISTS(409, "이미 존재하는 이메일입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    ARTICLE_NOT_FOUND(404, "존재하지 않는 게시글입니다.");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}

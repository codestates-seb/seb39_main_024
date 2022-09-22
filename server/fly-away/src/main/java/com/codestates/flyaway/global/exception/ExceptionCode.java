package com.codestates.flyaway.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    IMAGE_NOT_FOUND(404, "이미지가 존재하지 않습니다."),
    EMAIL_ALREADY_EXISTS(409, "이미 존재하는 이메일입니다."),
    FILE_NOT_FOUND(404, "파일이 존재하지 않습니다."),
    FILE_DELETE_FAILED(404, "파일을 삭제에 실패했습니다."),
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

package com.codestates.flyaway.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    IMAGE_NOT_FOUND(404, "존재하지 않는 이미지입니다."),
    EMAIL_ALREADY_EXISTS(409, "이미 존재하는 이메일입니다."),
    EMAIL_NOT_VALID(400, "유효하지 않은 이메일 형식입니다."),
    PASSWORD_NOT_VALID(400, "유효하지 않은 비밀번호 형식입니다."),
    FILE_NOT_FOUND(404, "파일이 존재하지 않습니다."),
    FILE_DELETE_FAILED(404, "파일을 삭제에 실패했습니다.");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}

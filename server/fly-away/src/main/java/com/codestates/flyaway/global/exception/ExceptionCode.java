package com.codestates.flyaway.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    MEMBER_NOT_AUTHORIZED(403, "로그인되지 않은 사용자입니다."),
    MEMBER_ALREADY_AUTHORIZED(400, "이미 로그인 상태입니다."),
    NOT_AUTHORIZED(403, "작성자가 일치하지 않습니다."),

    EMAIL_ALREADY_EXISTS(409, "이미 존재하는 이메일입니다."),
    EMAIL_NOT_EXISTS(400, "존재하지 않는 이메일입니다."),
    PASSWORD_NOT_MATCH(400, "비밀번호가 일치하지 않습니다."),

    IMAGE_NOT_FOUND(404, "이미지가 존재하지 않습니다."),
    FILE_NOT_FOUND(404, "파일이 존재하지 않습니다."),
    FILE_DELETE_FAILED(404, "파일 삭제에 실패했습니다."),

    FILE_CAN_NOT_SAVE(404, "파일을 저장하지 못했습니다."),

    ARTICLE_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
    COMMENT_NOT_FOUND(404, "존재하지 않는 댓글입니다."),
    CATEGORY_NOT_FOUND(404, "존재하지 않는 카테고리 입니다.");


    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}

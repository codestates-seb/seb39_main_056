package com.noterror.app.api.global.exception;


import lombok.Getter;

public enum ExceptionCode {
    //Product
    PRODUCT_BAD_REQUEST(400, "잘못된 요청입니다. (제품)"),
    PRODUCT_NOT_FOUND(404, "조회된 제품이 없습니다."),
    PRODUCT_SOLD_OUT(409,"품절된 제품입니다."),


    //Member
    MEMBER_BAD_REQUEST(400, "잘못된 요청입니다. (유저)"),
    MEMBER_EXISTS(409, "이미 존재하는 회원입니다."),
    MEMBER_UNAUTHORIZED(401, "권한이 없는 사용자입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    MEMBER_NOT_AUTHENTICATION(403, "인증이 필요한 사용자입니다."),

    //Server
    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    NOT_IMPLEMENTATION(501, "아직 구현되지 않았습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

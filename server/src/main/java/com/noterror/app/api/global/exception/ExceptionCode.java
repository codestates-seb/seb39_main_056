package com.noterror.app.api.global.exception;


import lombok.Getter;

public enum ExceptionCode {
    //Product
    PRODUCT_BAD_REQUEST(400, "Bad request to post a Product"),
    PRODUCT_NOT_FOUND(404, "Product not fond"),


    //Member
    MEMBER_BAD_REQUEST(400, "Bad request to post a Member"), //아이디 or 비밀번호가 틀렸습니다.
    MEMBER_UNAUTHORIZED(401, "Not Authorization"), //(인가) 권한이 없는 사용자입니다.
    MEMBER_NOT_FOUND(404, "Member not found"),  //존재하지 않는 회원입니다.
    MEMBER_NOT_AUTHENTICATION(403, "Not Authentication"),  //(인증) 인증이 필요한 사용자입니다.

    //Server
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    NOT_IMPLEMENTATION(501, "Not Implementation");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

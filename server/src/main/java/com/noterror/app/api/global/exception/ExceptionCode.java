package com.noterror.app.api.global.exception;


import lombok.Getter;

public enum ExceptionCode {
    //Product
    PRODUCT_BAD_REQUEST(400, "Bad request to post a Product"),
    PRODUCT_NOT_FOUND(404, "Product not fond"),


    //Member
    MEMBER_BAD_REQUEST(400, "Bad request"),
    MEMBER_UNAUTHORIZED(401, "Unauthorized"), //인증되지 않은 사용자입니다.
    MEMBER_NOT_FOUND(404, "Member not found"),  //존재하지 않는 회원입니다.
    MEMBER_NOT_AUTHORIZATION(403, "Not Authorization"),  //로그인이 필요합니다.

    //Server
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    NOT_IMPLEMENTATION(501, "Implementation");


    //Token
    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

package com.noterror.app.api.global.exception;


import lombok.Getter;

@Getter
public enum ExceptionCode {
    //Product
    PRODUCT_BAD_REQUEST(400, "(제품) 잘못된 요청입니다."),
    PRODUCT_NOT_FOUND(404, "조회된 제품이 없습니다."),
    PRODUCT_SOLD_OUT(409, "주문 수량이 재고 수량을 넘었습니다."),

    //Member
    MEMBER_BAD_REQUEST(400, "(유저) 잘못된 요청입니다."),
    MEMBER_UNAUTHORIZED(401, "회원 인증이 필요합니다."),
    MEMBER_AUTH_FAIL(401, "이메일과 패스워드가 잘못되었습니다."),
    MEMBER_EXPIRED_TOKEN(401, "토큰이 만료된 회원입니다."),
    MEMBER_EXISTS(409, "이미 존재하는 회원입니다."),
    MEMBER_FORBIDDEN(403, "권한이없습니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),

    //VegetarianType
    TYPE_BAD_REQUEST(400, "잘못된 채식 유형입니다."),

    //Global
    BAD_REQUEST(400, "잘못된 요청입니다."),

    //Server
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다."),
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

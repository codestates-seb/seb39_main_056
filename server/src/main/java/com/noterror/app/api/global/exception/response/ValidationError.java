package com.noterror.app.api.global.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 유효성 검증 관련 Exception
 * DTO에 지정한 유효성 검증에 대한 예외 처리
 * 어떤 필드에서 어떤 값으로 에러가 났는지를 응답 값으로 주기 위함
 * BingingResult : 검증 오류를 보관하는 객체
 * FieldError : BingingResult에 보관되는 오류 객체 + 필드에 오류가 있는 경우 발생하는 객체
 */
@Getter
@AllArgsConstructor
public class ValidationError {
    private String field;
    private Object rejectedValue;
    private String reason;
}

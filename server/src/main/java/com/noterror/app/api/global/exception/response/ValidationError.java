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

    public static List<ValidationError> of(BindingResult bindingResult){
        final List<FieldError> fieldErrors =
                bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(error -> new ValidationError(
                        error.getField(),           //오류 필드를 가져옴
                        error.getRejectedValue() == null ?      //사용자가 입력한 값 = 거절된 값
                                "" : error.getRejectedValue().toString(),  //null값을 빈 문자열로 바꿀 수 있음
                        error.getDefaultMessage()))  //기본 오류 메시지
                            .collect(Collectors.toList());
    }
}

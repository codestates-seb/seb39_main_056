package com.noterror.app.api.global.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 제약 조건이 위배됐을 때 발생하는 Exception
 * ConstraintViolation : 실패 정보를 보관 하는 객체
 * 제약 조건 : min, max, notblack, pattern, notnull, size, notempty 등
 */

@AllArgsConstructor
@Getter
public class ConstraintError {
    private String propertyPath;
    private Object rejectedValue;
    private String reason;

    public static List<ConstraintError> of(Set<ConstraintViolation<?>> constraintViolation){
        return constraintViolation.stream()
                .map(error -> new ConstraintError(
                        error.getPropertyPath().toString(),
                        error.getInvalidValue().toString(),
                        error.getMessage()))
                .collect(Collectors.toList());
    }
}

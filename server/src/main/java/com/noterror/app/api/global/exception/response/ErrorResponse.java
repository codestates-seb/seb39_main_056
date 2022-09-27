package com.noterror.app.api.global.exception.response;

import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<ValidationError> validationError;

    private List<ConstraintError> constraintError;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(List<ValidationError> validationError, List<ConstraintError> constraintError) {
        this.validationError = validationError;
        this.constraintError = constraintError;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(ValidationError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> constraintViolation) {
        return new ErrorResponse(null, ConstraintError.of(constraintViolation));
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase()); //상태코드와 범위별의미
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message){
        return new ErrorResponse(httpStatus.value(), message);
    }
}

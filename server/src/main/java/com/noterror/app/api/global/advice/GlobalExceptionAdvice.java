package com.noterror.app.api.global.advice;

import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.api.global.exception.response.ConstraintError;
import com.noterror.app.api.global.exception.response.ErrorResponse;
import com.noterror.app.api.global.exception.response.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity handleValidException(MethodArgumentNotValidException e) {
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ValidationError> errors =
                fieldErrors.stream()
                        .map(error -> new ValidationError(
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()))
                        .collect(Collectors.toList());

        return new ResponseEntity(new ErrorResponse(
                ExceptionCode.BAD_REQUEST.getStatus(),
                ExceptionCode.BAD_REQUEST.getMessage()
                , errors, null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleConstraintException(ConstraintViolationException e) {
        final Set<ConstraintViolation<?>> fieldErrors = e.getConstraintViolations();

        List<ConstraintError> errors =
                fieldErrors.stream()
                        .map(error -> new ConstraintError(
                                error.getPropertyPath().toString(), //경로
                                error.getInvalidValue().toString(), //잘못된 값
                                error.getMessage()))
                        .collect(Collectors.toList());

        return new ResponseEntity(new ErrorResponse(
                ExceptionCode.BAD_REQUEST.getStatus(),
                ExceptionCode.BAD_REQUEST.getMessage()
                , null, errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}

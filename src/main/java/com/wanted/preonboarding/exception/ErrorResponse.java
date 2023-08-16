package com.wanted.preonboarding.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private int status;

    private String message;

    private List<String> fieldErrors;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ErrorResponse(int status, List<String> fieldErrors) {
        this.status = status;
        this.fieldErrors = fieldErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();

        return new ErrorResponse(400,
                errors.stream()
                .map(error -> error.getDefaultMessage()
                ).collect(Collectors.toList()));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }
}

package com.wanted.preonboarding.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity(ErrorResponse.of(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        return new ResponseEntity(ErrorResponse.of(e.getExceptionCode()), HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}

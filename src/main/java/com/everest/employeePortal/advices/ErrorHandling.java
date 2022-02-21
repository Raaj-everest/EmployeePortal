package com.everest.employeePortal.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(exception.getLocalizedMessage());
        response.setLocalDateTime(LocalDateTime.now());
        return response;
    }


}

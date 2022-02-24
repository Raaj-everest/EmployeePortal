package com.everest.employeePortal.advices;

import com.everest.employeePortal.exceptions.EmployeeNotFoundException;
import com.everest.employeePortal.exceptions.EmailIdAlreadyExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ErrorHandling{

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse();
        List<String> errors = new ArrayList<String>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " : " + error.getDefaultMessage());
        }
        response.setLocalDateTime(LocalDateTime.now());
        response.setMessage(errors);
        return response;
    }
    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRuntimeException(EmployeeNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(Collections.singletonList(exception.getLocalizedMessage()));
        response.setLocalDateTime(LocalDateTime.now());
        return response;
    }

    @ExceptionHandler({EmailIdAlreadyExistedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRuntimeException(EmailIdAlreadyExistedException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(Collections.singletonList(exception.getLocalizedMessage()));
        response.setLocalDateTime(LocalDateTime.now());
        return response;
    }


}

package com.everest.employeePortal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailIdAlreadyExistedException extends RuntimeException{
    public EmailIdAlreadyExistedException(String message) {
        super(message);
    }
}

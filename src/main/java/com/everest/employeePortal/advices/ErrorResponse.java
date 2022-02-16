package com.everest.employeePortal.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private String message;
}

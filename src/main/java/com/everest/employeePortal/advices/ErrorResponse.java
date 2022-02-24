package com.everest.employeePortal.advices;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private List<String> message;
}

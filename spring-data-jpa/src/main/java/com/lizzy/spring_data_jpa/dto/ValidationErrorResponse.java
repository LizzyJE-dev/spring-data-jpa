package com.lizzy.spring_data_jpa.dto;

import java.time.LocalDateTime;

public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;

}

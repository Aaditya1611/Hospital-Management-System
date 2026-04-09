package com.example.auth.api.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class ApiError {
    
    private LocalDateTime timestamp;
    private String error;
    private HttpStatusCode statusCode;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatusCode statusCode) {

        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}

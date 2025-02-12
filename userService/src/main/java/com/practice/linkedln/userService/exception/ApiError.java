package com.practice.linkedln.userService.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }

    

    

}

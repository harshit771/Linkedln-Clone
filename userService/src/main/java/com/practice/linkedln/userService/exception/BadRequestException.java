package com.practice.linkedln.userService.exception;

public class BadRequestException extends RuntimeException{


    public BadRequestException(String message){
        super(message);
    }
}

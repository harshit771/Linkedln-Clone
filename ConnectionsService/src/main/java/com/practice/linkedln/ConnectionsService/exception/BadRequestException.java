package com.practice.linkedln.ConnectionsService.exception;

public class BadRequestException extends RuntimeException{


    public BadRequestException(String message){
        super(message);
    }
}

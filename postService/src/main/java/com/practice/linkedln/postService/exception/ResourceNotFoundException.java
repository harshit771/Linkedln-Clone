package com.practice.linkedln.postService.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String message){
        super(message);
    }

}

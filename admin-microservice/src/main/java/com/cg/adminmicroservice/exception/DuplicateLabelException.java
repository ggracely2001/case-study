package com.cg.adminmicroservice.exception;

public class DuplicateLabelException extends RuntimeException{
    public DuplicateLabelException(String message){
        super(message);
    }
}

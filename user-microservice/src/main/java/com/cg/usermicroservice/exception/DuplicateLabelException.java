package com.cg.usermicroservice.exception;

public class DuplicateLabelException extends RuntimeException{
    public DuplicateLabelException(String message){
        super(message);
    }
}

package com.cg.adminmicroservice.exception;

public class API_requestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public API_requestException(String message, Throwable cause) {
        super(message, cause);
        this.setMessage(message);
    }

    public API_requestException(String message) {
        super(message);
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

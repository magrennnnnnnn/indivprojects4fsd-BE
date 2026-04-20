package com.prolink.prolink.exceptionhandler;

public class InvalidWorkStartDateException extends RuntimeException {
    public InvalidWorkStartDateException(String message) {
        super(message);
    }
}

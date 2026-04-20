package com.prolink.prolink.exceptionhandler;

public class InvalidWorkEndDateException extends RuntimeException {
    public InvalidWorkEndDateException(String message) {
        super(message);
    }
}

package com.prolink.prolink.exceptionhandler;

public class InvalidPostTextException extends RuntimeException {
    public InvalidPostTextException(String message) {
        super(message);
    }
}

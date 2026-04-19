package com.prolink.prolink.exceptionhandler;

public class InvalidProfileNameException extends RuntimeException {
    public InvalidProfileNameException(String message) {
        super(message);
    }
}

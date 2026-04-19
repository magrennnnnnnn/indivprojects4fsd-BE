package com.prolink.prolink.exceptionhandler;

public class InvalidProfilePersonalDetailsException extends RuntimeException {
    public InvalidProfilePersonalDetailsException(String message) {
        super(message);
    }
}

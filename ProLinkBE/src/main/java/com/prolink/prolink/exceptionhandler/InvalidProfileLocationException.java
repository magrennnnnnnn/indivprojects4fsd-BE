package com.prolink.prolink.exceptionhandler;

public class InvalidProfileLocationException extends RuntimeException {
    public InvalidProfileLocationException(String message) {
        super(message);
    }
}

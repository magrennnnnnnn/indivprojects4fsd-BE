package com.prolink.prolink.exceptionhandler;

public class InvalidEducationStartDateException extends RuntimeException {
    public InvalidEducationStartDateException(String message) {
        super(message);
    }
}

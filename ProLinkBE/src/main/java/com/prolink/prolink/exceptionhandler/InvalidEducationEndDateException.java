package com.prolink.prolink.exceptionhandler;

public class InvalidEducationEndDateException extends RuntimeException {
    public InvalidEducationEndDateException(String message) {
        super(message);
    }
}

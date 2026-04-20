package com.prolink.prolink.exceptionhandler;

public class InvalidWorkInstitutionNameException extends RuntimeException {
    public InvalidWorkInstitutionNameException(String message) {
        super(message);
    }
}

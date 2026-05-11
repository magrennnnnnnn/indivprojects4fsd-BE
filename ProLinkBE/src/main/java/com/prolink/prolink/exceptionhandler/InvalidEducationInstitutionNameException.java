package com.prolink.prolink.exceptionhandler;

public class InvalidEducationInstitutionNameException extends RuntimeException {
    public InvalidEducationInstitutionNameException(String message) {
        super(message);
    }
}

package com.prolink.prolink.exceptionhandler;

public class InvalidCourseStartDateException extends RuntimeException {
    public InvalidCourseStartDateException(String message) {
        super(message);
    }
}

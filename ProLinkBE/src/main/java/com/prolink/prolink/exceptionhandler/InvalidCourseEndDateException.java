package com.prolink.prolink.exceptionhandler;

public class InvalidCourseEndDateException extends RuntimeException {
    public InvalidCourseEndDateException(String message) {
        super(message);
    }
}

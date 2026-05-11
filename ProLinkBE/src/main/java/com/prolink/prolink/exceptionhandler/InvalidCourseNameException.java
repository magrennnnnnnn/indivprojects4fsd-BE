package com.prolink.prolink.exceptionhandler;

public class InvalidCourseNameException extends RuntimeException {
    public InvalidCourseNameException(String message) {
        super(message);
    }
}

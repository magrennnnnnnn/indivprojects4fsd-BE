package com.prolink.prolink.exceptionhandler;

public class InvalidPostTitleException extends RuntimeException {
    public InvalidPostTitleException(String message) {
        super(message);
    }
}

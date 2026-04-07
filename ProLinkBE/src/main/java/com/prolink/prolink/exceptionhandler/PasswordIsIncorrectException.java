package com.prolink.prolink.exceptionhandler;

public class PasswordIsIncorrectException extends RuntimeException{
    public PasswordIsIncorrectException(String message)  {
        super(message);
    }
}
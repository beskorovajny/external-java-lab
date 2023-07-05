package com.epam.esm.core.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){}
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    public UserAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

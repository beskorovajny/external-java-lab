package com.epam.esm.core.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {}

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

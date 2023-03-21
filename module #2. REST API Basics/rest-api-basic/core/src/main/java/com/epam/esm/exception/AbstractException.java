package com.epam.esm.exception;


public abstract class AbstractException extends RuntimeException {
    private static final long serialVersionUID = 3591985503083520328L;

    protected AbstractException() {
    }
    protected AbstractException(String message) {
        super(message);
    }
    protected AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

}

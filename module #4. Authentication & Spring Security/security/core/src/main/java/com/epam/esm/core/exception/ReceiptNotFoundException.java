package com.epam.esm.core.exception;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException() {
    }

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    public ReceiptNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

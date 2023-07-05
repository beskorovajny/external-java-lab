package com.epam.esm.core.exception;

public class GiftCertificateAlreadyExistsException extends RuntimeException {
    public GiftCertificateAlreadyExistsException() {
    }

    public GiftCertificateAlreadyExistsException(String message) {
        super(message);
    }

    public GiftCertificateAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

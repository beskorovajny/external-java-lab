package com.epam.esm.core.exception;

public class GiftCertificateNotFoundException extends RuntimeException {
    public GiftCertificateNotFoundException() {
    }

    public GiftCertificateNotFoundException(String message) {
        super(message);
    }

    public GiftCertificateNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

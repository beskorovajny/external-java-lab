package com.epam.esm.exception.model;

import com.epam.esm.exception.AbstractException;

public class GiftCertificateNotFoundException extends AbstractException {
    private static final long serialVersionUID = 3390196352737293254L;
    public GiftCertificateNotFoundException() {
    }

    public GiftCertificateNotFoundException(String message) {
        super(message);
    }

    public GiftCertificateNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

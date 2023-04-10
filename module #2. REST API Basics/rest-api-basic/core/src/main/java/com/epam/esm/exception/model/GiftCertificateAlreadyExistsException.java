package com.epam.esm.exception.model;

import com.epam.esm.exception.AbstractException;

public class GiftCertificateAlreadyExistsException extends AbstractException {
    private static final long serialVersionUID = -5057233134287184159L;
    public GiftCertificateAlreadyExistsException() {
    }

    public GiftCertificateAlreadyExistsException(String message) {
        super(message);
    }

    public GiftCertificateAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

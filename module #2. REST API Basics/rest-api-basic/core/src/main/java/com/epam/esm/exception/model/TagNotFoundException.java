package com.epam.esm.exception.model;

import com.epam.esm.exception.AbstractException;

public class TagNotFoundException extends AbstractException {
    private static final long serialVersionUID = -5195050982151673083L;

    public TagNotFoundException() {
    }

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

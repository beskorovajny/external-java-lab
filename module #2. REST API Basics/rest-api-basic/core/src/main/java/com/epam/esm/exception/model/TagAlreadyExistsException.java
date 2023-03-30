package com.epam.esm.exception.model;

import com.epam.esm.exception.AbstractException;

public class TagAlreadyExistsException extends AbstractException {
    private static final long serialVersionUID = -4207926947559046542L;

    public TagAlreadyExistsException() {
    }

    public TagAlreadyExistsException(String message) {
        super(message);
    }

    public TagAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

package com.epam.esm.controller.errors;

import com.epam.esm.exception.model.GiftCertificateAlreadyExistsException;
import com.epam.esm.exception.model.GiftCertificateNotFoundException;
import com.epam.esm.exception.model.TagAlreadyExistsException;
import com.epam.esm.exception.model.TagNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String LOG_MSG = "[ExceptionHandler] Handled {} exception/error...";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(LOG_MSG, "IllegalArgumentException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(TagNotFoundException exception) {
        log.error(LOG_MSG, "TagNotFoundException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(TagAlreadyExistsException exception) {
        log.error(LOG_MSG, "TagAlreadyExistsException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(GiftCertificateNotFoundException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(GiftCertificateNotFoundException exception) {
        log.error(LOG_MSG, "GiftCertificateNotFoundException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(GiftCertificateAlreadyExistsException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(GiftCertificateAlreadyExistsException exception) {
        log.error(LOG_MSG, "GiftCertificateAlreadyExistsException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        log.debug("ResponseEntity: [{}] created.", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getErrorCode()));
    }
}

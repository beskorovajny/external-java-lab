package com.epam.esm.api.errors;

import com.epam.esm.core.exception.*;
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
    private static final String EXCEPTION_CODE = "01";
    private static final String ILLEGAL_ARGUMENT_CODE = "02";
    private static final String NULL_POINTER_CODE = "03";
    private static final String TAG_NOT_FOUND_CODE = "04";
    private static final String TAG_ALREADY_EXISTS_CODE = "05";
    private static final String GIFT_CERTIFICATE_NOT_FOUND_CODE = "06";
    private static final String GIFT_CERTIFICATE_ALREADY_EXISTS_CODE = "07";
    private static final String USER_NOT_FOUND_CODE = "09";
    private static final String LOG_MSG = "[ExceptionHandler] Handled {} exception/error...";
    private static final String NULL_POINTER = "NullPointerException occurs...";
    private static final String ILLEGAL_ARGUMENT = "Illegal argument...";
    private static final String FIVE_XX = "Ooooops, something went wrong....";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(LOG_MSG, "IllegalArgumentException");
        return buildResponseEntity(new ErrorResponse(ILLEGAL_ARGUMENT, String.format("%d%s",
                HttpStatus.BAD_REQUEST.value(), ILLEGAL_ARGUMENT_CODE)));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNPE(NullPointerException exception) {
        log.error(LOG_MSG, "NullPointerException");
        return buildResponseEntity(new ErrorResponse(NULL_POINTER, String.format("%d%s",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), NULL_POINTER_CODE)));
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Object> handleTagNotFoundException(TagNotFoundException exception) {
        log.error(LOG_MSG, "TagNotFoundException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), String.format("%d%s",
                HttpStatus.NOT_FOUND.value(), TAG_NOT_FOUND_CODE)));
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<Object> handleTagAlreadyExistsException(TagAlreadyExistsException exception) {
        log.error(LOG_MSG, "TagAlreadyExistsException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), String.format("%d%s",
                HttpStatus.BAD_REQUEST.value(), TAG_ALREADY_EXISTS_CODE)));
    }

    @ExceptionHandler(GiftCertificateNotFoundException.class)
    public ResponseEntity<Object> handleGiftCertificateNotFoundException(GiftCertificateNotFoundException exception) {
        log.error(LOG_MSG, "GiftCertificateNotFoundException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), String.format("%d%s",
                HttpStatus.NOT_FOUND.value(), GIFT_CERTIFICATE_NOT_FOUND_CODE)));
    }

    @ExceptionHandler(GiftCertificateAlreadyExistsException.class)
    public ResponseEntity<Object> handleGiftCertificateAlreadyExistsException(GiftCertificateAlreadyExistsException exception) {
        log.error(LOG_MSG, "GiftCertificateAlreadyExistsException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), String.format("%d%s",
                HttpStatus.BAD_REQUEST.value(), GIFT_CERTIFICATE_ALREADY_EXISTS_CODE)));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        log.error(LOG_MSG, "UserNotFoundException");
        return buildResponseEntity(new ErrorResponse(exception.getMessage(), String.format("%d%s",
                HttpStatus.NOT_FOUND.value(), USER_NOT_FOUND_CODE)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(LOG_MSG, "Exception");
        return buildResponseEntity(new ErrorResponse(e.getMessage(), String.format("%d%s",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), EXCEPTION_CODE)));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        log.debug("ResponseEntity created for errorResponse: [{}]", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(Integer.parseInt(errorResponse
                .getErrorCode().substring(0, 3))));
    }
}

package com.yalov4uk.exception_handlers;

import com.yalov4uk.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by valera on 5/21/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Exception> notFoundHandler(Exception e) throws Exception {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Exception> httpRequestMethodNotSupportedExceptionHandler(Exception e) throws Exception {
        return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Exception> defaultErrorHandler(Exception e) throws Exception {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}

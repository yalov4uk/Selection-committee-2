package com.yalov4uk.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by valera on 5/21/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> defaultErrorHandler(Exception e) throws Exception {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

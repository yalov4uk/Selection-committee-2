package com.yalov4uk.controllers.exception_handlers;

import com.yalov4uk.controllers.abstracts.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by valera on 5/21/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler extends BaseController {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NpeHandler(Exception e) throws Exception {
        logger.error(e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(Exception e) throws Exception {
        logger.error(e);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

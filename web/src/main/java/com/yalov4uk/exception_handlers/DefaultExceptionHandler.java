package com.yalov4uk.exception_handlers;

import com.yalov4uk.abstracts.BaseController;
import com.yalov4uk.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by valera on 5/21/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler extends BaseController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity NotFoundHandler(Exception e) throws Exception {
        logger.error(e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(Exception e) throws Exception {
        logger.error(e);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

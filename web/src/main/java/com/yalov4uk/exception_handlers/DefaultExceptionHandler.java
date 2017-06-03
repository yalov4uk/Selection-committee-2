package com.yalov4uk.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by valera on 5/21/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public void UsernameNotFoundHandler(Exception e, HttpServletResponse response) throws Exception {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(Exception e, HttpServletResponse response) throws Exception {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}

package com.yalov4uk.exceptions;

/**
 * Created by valera on 5/22/17.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(Exception e) {
        super(e);
    }
}

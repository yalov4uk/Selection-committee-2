package com.yalov4uk.exceptions;

public class DaoUncheckedException extends RuntimeException {

    public DaoUncheckedException() {
    }

    public DaoUncheckedException(String msg) {
        super(msg);
    }

    public DaoUncheckedException(Exception e) {
        super(e);
    }
}

package com.yalov4uk.exceptions;

public class ServiceUncheckedException extends RuntimeException {

    public ServiceUncheckedException() {
    }

    public ServiceUncheckedException(String msg) {
        super(msg);
    }

    public ServiceUncheckedException(Exception e) {
        super(e);
    }
}

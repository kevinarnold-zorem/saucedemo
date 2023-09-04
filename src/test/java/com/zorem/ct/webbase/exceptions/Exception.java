package com.zorem.ct.webbase.exceptions;

public class Exception extends RuntimeException {

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable t) {
        super(message, t);
    }
}

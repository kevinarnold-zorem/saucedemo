package com.zorem.ct.webbase.exceptions;

public class DriverInitializationException extends Exception {

    public DriverInitializationException(String message) {
        super(message);
    }

    public DriverInitializationException(String message, Throwable t) {
        super(message, t);
    }
}

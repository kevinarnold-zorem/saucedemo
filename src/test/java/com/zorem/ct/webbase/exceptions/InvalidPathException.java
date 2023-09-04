package com.zorem.ct.webbase.exceptions;

public class InvalidPathException extends Exception {
    public InvalidPathException(String message) {
        super(message);
    }

    public InvalidPathException(String message, Throwable t) {
        super(message, t);
    }
}

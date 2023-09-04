package com.zorem.ct.webbase.exceptions;

public class JsonFileUsageException extends Exception {
    public JsonFileUsageException(String message) {
        super(message);
    }

    public JsonFileUsageException(String message, Throwable t) {
        super(message, t);
    }
}

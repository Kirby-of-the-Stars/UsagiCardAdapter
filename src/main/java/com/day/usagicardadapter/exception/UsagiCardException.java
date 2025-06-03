package com.day.usagicardadapter.exception;

public class UsagiCardException extends RuntimeException {
    public UsagiCardException() {
        super();
    }
    public UsagiCardException(String message) {
        super(message);
    }
    public UsagiCardException(String message,Throwable cause) {
        super(message,cause);
    }

}

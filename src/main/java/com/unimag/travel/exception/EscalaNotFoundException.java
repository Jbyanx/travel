package com.unimag.travel.exception;

public class EscalaNotFoundException extends ResourceNotFoundException{
    public EscalaNotFoundException() {
    }

    public EscalaNotFoundException(String message) {
        super(message);
    }

    public EscalaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.unimag.travel.exception;

public class ReservaNotFoundException extends ResourceNotFoundException{
    public ReservaNotFoundException() {
    }

    public ReservaNotFoundException(String message) {
        super(message);
    }

    public ReservaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

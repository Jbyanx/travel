package com.unimag.travel.exception;

public class VueloNotFoundException extends ResourceNotFoundException{
    public VueloNotFoundException() {
    }

    public VueloNotFoundException(String message) {
        super(message);
    }

    public VueloNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

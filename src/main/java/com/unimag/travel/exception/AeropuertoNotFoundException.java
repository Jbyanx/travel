package com.unimag.travel.exception;

public class AeropuertoNotFoundException extends ResourceNotFoundException{
    public AeropuertoNotFoundException() {
    }

    public AeropuertoNotFoundException(String message) {
        super(message);
    }

    public AeropuertoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

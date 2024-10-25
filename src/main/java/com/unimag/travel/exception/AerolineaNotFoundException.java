package com.unimag.travel.exception;

public class AerolineaNotFoundException extends ResourceNotFoundException{
    public AerolineaNotFoundException() {
    }

    public AerolineaNotFoundException(String message) {
        super(message);
    }

    public AerolineaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

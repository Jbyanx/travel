package com.unimag.travel.exception;

public class ClienteNotFoundException extends ResourceNotFoundException{
    public ClienteNotFoundException() {
    }

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

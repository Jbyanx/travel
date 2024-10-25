package com.unimag.travel.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundHandler(ClientNotFoundException ex,
                                                                HttpServletRequest request,
                                                                HttpServletResponse response) {
        ErrorMessage errorMessage = new ErrorMessage(
          request.get
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}

package com.unimag.travel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public record ErrorMessage (
    Integer httpStatus,
    String url,
    String message,
    String backendMessage,
    LocalDateTime timestamp
){}

package com.unimag.travel.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorMessage (
    Integer httpStatus,
    String url,
    String method,
    String message,
    String backendMessage,
    LocalDateTime timestamp,
    List<String> details
){}

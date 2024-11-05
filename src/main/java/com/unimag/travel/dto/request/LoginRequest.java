package com.unimag.travel.dto.request;

public record LoginRequest(
        String correoElectronico,
        String password
) {
}

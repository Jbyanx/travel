package com.unimag.travel.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El campo 'correo electrónico' no debe estar vacío")
        String correoElectronico,
        @NotBlank(message = "El campo 'password' no debe estar vacío")
        String password
) {
}

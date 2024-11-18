package com.unimag.travel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveAerolinea(
        @NotBlank(message = "el nombre de la aerolinea no debe ir vacio")
        @Size(max = 100)
        String nombre,
        long codigo,
        @NotBlank(message = "el nombre de la aerolinea no debe ir vacio")
        @Size(max = 100)
        String pais
) implements Serializable {}

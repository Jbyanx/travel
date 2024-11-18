package com.unimag.travel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveAeropuerto(
        @NotBlank(message = "el nombre del aeropuerto no debe ir vacio")
        @Size(max = 100)
        String nombre,
        @NotBlank(message = "la ciudad del aeropuerto no debe ir vacia")
        @Size(max = 100)
        String ciudad,
        @NotBlank(message = "el pais del aeropuerto no debe ir vacio")
        @Size(max = 100)
        String pais
) implements Serializable {
}

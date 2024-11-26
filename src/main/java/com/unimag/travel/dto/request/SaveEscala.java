package com.unimag.travel.dto.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Duration;

public record SaveEscala(
        @NotNull
        Long idVuelo,
        @NotNull
        Long idAeropuerto,
        @NotNull
        Duration duracion
) implements Serializable {
}

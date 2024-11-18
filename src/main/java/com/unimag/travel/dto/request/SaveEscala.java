package com.unimag.travel.dto.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Duration;

public record SaveEscala(
        @NotNull
        long idVuelo,
        @NotNull
        long idAeropuerto,
        @NotNull
        Duration duracion
) implements Serializable {
}

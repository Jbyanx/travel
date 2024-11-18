package com.unimag.travel.dto.request;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record SaveVuelo(
        @NotBlank(message = "la ciudad origen del vuelo no debe ir vacia")
        @Size(max = 100)
        String origen,
        @NotBlank(message = "la ciudad destino del vuelo no debe ir vacia")
        @Size(max = 100)
        String destino,
        @FutureOrPresent
        LocalDate fechaDeSalida,
        @NotNull
        LocalTime horaDeSalida,
        @NotNull
        Duration duracion,
        @Min(value = 1, message = "el vuelo no puede ir sin pasajeros")
        int capacidad,
        @NotNull
        long idAerolinea,
        @NotNull
        long idAeropuertoOrigen,
        @NotNull
        long idAeropuertoDestino
) implements Serializable {
}

package com.unimag.travel.dto.request;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record SaveVuelo(
        String origen,
        String destino,
        LocalDate fechaDeSalida,
        LocalTime horaDeSalida,
        Duration duracion,
        int capacidad
) implements Serializable {
}

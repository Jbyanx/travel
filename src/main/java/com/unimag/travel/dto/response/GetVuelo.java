package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record GetVuelo(
        long id,
        String origen,
        String destino,
        LocalDate fechaDeSalida,
        LocalTime horaDeSalida,
        Duration duracion,
        int capacidad,
        String aerolinea,
        String aeropuertoOrigen,
        String aeropuertoDestino,
        List<GetEscala> escalas
) implements Serializable {
}

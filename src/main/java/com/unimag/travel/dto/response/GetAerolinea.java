package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record GetAerolinea(
        long id,
        String nombre,
        long codigo,
        String pais,
        List<GetVuelo> vuelos
) implements Serializable {
    public static record GetVuelo(
            long id,
            String origen,
            String destino,
            LocalDate fechaDeSalida,
            LocalTime horaDeSalida,
            Duration duracion,
            int capacidad,
            String aeropuertoOrigen,
            String aeropuertoDestino
    ) implements Serializable {
        public static record GetEscala(
                String aeropuerto
        ) implements Serializable {}
    }
}

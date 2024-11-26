package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.time.Duration;

public record GetEscala(
        Long id,
        GetAeropuerto aeropuerto,
        Long idVuelo,
        Duration duracion
) implements Serializable {}

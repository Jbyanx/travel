package com.unimag.travel.dto.request;

import java.io.Serializable;
import java.time.Duration;

public record SaveEscala(
        long idVuelo,
        long idAeropuerto,
        Duration duracion
) implements Serializable {
}

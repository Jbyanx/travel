package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

public record GetReserva(
        Long id,
        String cliente,
        GetVuelo vuelo,
        LocalDate fechaDeReserva,
        int numeroDePasajeros
) implements Serializable {
}

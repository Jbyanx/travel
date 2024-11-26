package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

public record GetReserva(
        Long id,
        String cliente,
        Long idVuelo,
        LocalDate fechaDeReserva,
        Integer numeroDePasajeros
) implements Serializable {
}

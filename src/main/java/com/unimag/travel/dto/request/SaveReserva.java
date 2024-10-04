package com.unimag.travel.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

public record SaveReserva(
        long idCliente,
        long idVuelo,
        LocalDate fechaDeReserva,
        int numeroDePasajeros
) implements Serializable {
}

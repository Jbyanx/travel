package com.unimag.travel.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public record SaveReserva(
        @NotNull
        long idCliente,
        @NotNull
        long idVuelo,
        @FutureOrPresent
        LocalDate fechaDeReserva,
        @Min(value = 1, message = "el numero minimo de pasajeros es 1")
        int numeroDePasajeros
) implements Serializable {
}

package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetCliente(
        long id,
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String correoElectronico,
        List<GetReserva> reservas,
        List<GetVuelo> vuelos
) implements Serializable {
}

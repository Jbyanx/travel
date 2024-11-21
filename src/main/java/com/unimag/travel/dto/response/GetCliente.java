package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetCliente(
        Long id,
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String correoElectronico,
        List<Long> idReservas,
        List<Long> idVuelos
) implements Serializable {
}

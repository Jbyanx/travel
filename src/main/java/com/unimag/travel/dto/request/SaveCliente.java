package com.unimag.travel.dto.request;

import java.io.Serializable;

public record SaveCliente(
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String correoElectronico
) implements Serializable {
}

package com.unimag.travel.dto.request;

import java.io.Serializable;
import java.util.Set;

public record SignupRequest(
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String correoElectronico,
        String password
) implements Serializable {
}

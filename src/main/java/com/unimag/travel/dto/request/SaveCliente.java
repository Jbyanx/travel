package com.unimag.travel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveCliente(
        @NotBlank(message = "el nombre del cliente no debe ir vacio")
        @Size(max = 100)
        String nombre,
        @NotBlank(message = "el apellido del cliente no debe ir vacio")
        @Size(max = 100)
        String apellido,
        @NotBlank(message = "la direccion del cliente no debe ir vacia")
        @Size(max = 100)
        String direccion,
        @NotBlank(message = "el telefono del cliente no debe ir vacio")
        @Size(max = 100)
        String telefono,
        @NotBlank(message = "el correo electronico del cliente no debe ir vacio")
        @Size(max = 255)
        @Email(message = "formato de correo electronico invalido")
        String correoElectronico
) implements Serializable {
}

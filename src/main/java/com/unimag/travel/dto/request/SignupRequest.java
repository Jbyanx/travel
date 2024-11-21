package com.unimag.travel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Set;

public record SignupRequest(
        @NotBlank(message = "El campo 'nombre' no debe estar vacío")
        String nombre,
        @NotBlank(message = "El campo 'apellido' no debe estar vacío")
        String apellido,
        @NotBlank(message = "El campo 'direccion' no debe estar vacío")
        String direccion,
        @NotBlank(message = "El campo 'telefono' no debe estar vacío")
        String telefono,
        @NotBlank(message = "El campo 'correoElectronico' no debe estar vacío")
        @Email(message = "formato de correo electronico invalido")
        String correoElectronico,
        @NotBlank(message = "El campo 'password' no debe estar vacío")
        String password
) implements Serializable {
}

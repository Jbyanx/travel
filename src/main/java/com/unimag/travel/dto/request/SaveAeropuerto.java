package com.unimag.travel.dto.request;

import java.io.Serializable;

public record SaveAeropuerto(
        String nombre,
        String ciudad,
        String pais
) implements Serializable {
}

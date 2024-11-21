package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetAeropuerto(
        Long id,
        String nombre,
        String ciudad,
        String pais,
        List<Long> idVuelosOrigen,
        List<Long> idVuelosDestino
) implements Serializable {
}

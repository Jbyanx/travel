package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetAeropuerto(
        long id,
        String nombre,
        String ciudad,
        String pais,
        List<GetVuelo> vuelosOrigen,
        List<GetVuelo> vuelosDestino
) implements Serializable {
}

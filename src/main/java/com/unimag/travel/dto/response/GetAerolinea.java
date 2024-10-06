package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetAerolinea(
        long id,
        String nombre,
        long codigo,
        String pais,
        List<GetVuelo> vuelos
) implements Serializable {

}

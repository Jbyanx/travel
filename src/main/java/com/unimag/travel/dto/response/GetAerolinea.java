package com.unimag.travel.dto.response;

import java.io.Serializable;
import java.util.List;

public record GetAerolinea(
        Long id,
        String nombre,
        Long codigo,
        String pais,
        List<Long> idVuelos
) implements Serializable {}

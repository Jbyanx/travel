package com.unimag.travel.dto.request;

import java.io.Serializable;

public record SaveAerolinea(
        String nombre,
        long codigo,
        String pais
) implements Serializable {}

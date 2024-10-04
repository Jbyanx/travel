package com.unimag.travel.dto.request;

import com.unimag.travel.dto.response.GetVuelo;

import java.io.Serializable;
import java.util.List;

public record SaveAeropuerto(
        String nombre,
        String ciudad,
        String pais
) implements Serializable {
}

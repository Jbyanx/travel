package com.unimag.travel.services;

import com.unimag.travel.entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

    Optional<Vuelo> getVueloById(Long id);

    Optional<Vuelo> getVueloByName(String name);

    List<Vuelo> getAllVuelos();

    Vuelo saveVuelo(Vuelo vuelo);

    Vuelo updateVueloById(Long id, Vuelo saveVuelo);

    void deleteVueloById(Long id);
}

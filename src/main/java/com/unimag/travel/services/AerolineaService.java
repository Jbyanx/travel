package com.unimag.travel.services;

import com.unimag.travel.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {

    Optional<Aerolinea> getAerolineaById(Long id);

    Optional<Aerolinea> getAerolineaByName(String name);

    List<Aerolinea> getAllAerolineas();

    Aerolinea saveAerolinea(Aerolinea aerolinea);

    Aerolinea updateAerolineaById(Long id, Aerolinea saveAerolinea);

    void deleteAerolineaById(Long id);
}

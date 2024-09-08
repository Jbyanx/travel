package com.unimag.travel.services;

import com.unimag.travel.entities.Aeropuerto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {

    Optional<Aeropuerto> getAeropuertoById(Long id);

    Optional<Aeropuerto> getAeropuertoByName(String name);

    List<Aeropuerto> getAllAeropuertos();

    Aeropuerto saveAeropuerto(Aeropuerto aeropuerto);

    Aeropuerto updateAeropuertoById(Long id, Aeropuerto saveAeropuerto);

    void deleteAeropuertoById(Long id);
}

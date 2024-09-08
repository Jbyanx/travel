package com.unimag.travel.services;

import com.unimag.travel.entities.Escala;

import java.util.List;
import java.util.Optional;

public interface EscalaService {

    Optional<Escala> getEscalaById(Long id);

    Optional<Escala> getEscalaByName(String name);

    List<Escala> getAllEscalas();

    Escala saveEscala(Escala escala);

    Escala updateEscalaById(Long id, Escala saveEscala);

    void deleteEscalaById(Long id);
}

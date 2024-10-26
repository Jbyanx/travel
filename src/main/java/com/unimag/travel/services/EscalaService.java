package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Escala;

import java.util.List;
import java.util.Optional;

public interface EscalaService {

    GetEscala getEscalaById(Long id);

    List<GetEscala> getAllEscalas();

    GetEscala saveEscala(SaveEscala saveEscala);

    GetEscala updateEscalaById(Long id, SaveEscala saveEscala);

    void deleteEscalaById(Long id);
}

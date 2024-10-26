package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {

    GetAeropuerto getAeropuertoById(Long id);

    GetAeropuerto getAeropuertoByName(String name);

    List<GetAeropuerto> getAllAeropuertos();

    GetAeropuerto saveAeropuerto(SaveAeropuerto saveAeropuerto);

    GetAeropuerto updateAeropuertoById(Long id, SaveAeropuerto saveAeropuerto);

    void deleteAeropuertoById(Long id);
}

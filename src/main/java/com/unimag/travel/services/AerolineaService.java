package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {

    GetAerolinea getAerolineaById(Long id);

    GetAerolinea getAerolineaByName(String name);

    List<GetAerolinea> getAllAerolineas();

    GetAerolinea saveAerolinea(SaveAerolinea saveAerolinea);

    GetAerolinea updateAerolineaById(Long id, SaveAerolinea saveAerolinea);

    void deleteAerolineaById(Long id);
}

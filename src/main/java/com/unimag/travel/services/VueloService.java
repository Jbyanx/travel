package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

    GetVuelo getVueloById(Long id);

    List<GetVuelo> getAllVuelos();

    GetVuelo saveVuelo(SaveVuelo saveVuelo);

    GetVuelo updateVueloById(Long id, SaveVuelo saveVuelo);

    void deleteVueloById(Long id);
}

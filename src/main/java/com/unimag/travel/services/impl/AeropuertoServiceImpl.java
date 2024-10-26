package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;
import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.exception.AeropuertoNotFoundException;
import com.unimag.travel.mapper.AeroPuertoMapper;
import com.unimag.travel.repositories.AerolineaRepository;
import com.unimag.travel.repositories.AeropuertoRepository;
import com.unimag.travel.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {
    private AeropuertoRepository aeropuertoRepository;

    @Autowired
    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public GetAeropuerto getAeropuertoById(Long id) {
        Aeropuerto aeropuerto = aeropuertoRepository.findById(id)
                .orElseThrow(()->new AeropuertoNotFoundException("aeropuerto id: "+id+" not found"));
        return AeroPuertoMapper.INSTANCE.aeropuertoToGetAeropuerto(aeropuerto);
    }

    @Override
    public GetAeropuerto getAeropuertoByName(String name) {
        Aeropuerto aeropuerto = aeropuertoRepository.getAeropuertoByNombre(name)
                .orElseThrow(()-> new AeropuertoNotFoundException("aeropuerto nombre: "+name+" not found"));
        return AeroPuertoMapper.INSTANCE.aeropuertoToGetAeropuerto(aeropuerto);
    }

    @Override
    public List<GetAeropuerto> getAllAeropuertos() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        return AeroPuertoMapper.INSTANCE.aeropuertoLisToGetAeropuertoList(aeropuertos);
    }

    @Override
    public GetAeropuerto saveAeropuerto(SaveAeropuerto saveAeropuerto) {
        //convertimos el save aeropuerto a aeropuerto
        Aeropuerto aeropuertoToSave = AeroPuertoMapper.INSTANCE.saveAeropuertoToAeropuerto(saveAeropuerto);
        //lo guardamos
        Aeropuerto savedAeropuerto = aeropuertoRepository.save(aeropuertoToSave);
        //lo devolvemos como GetAeropuerto
        return AeroPuertoMapper.INSTANCE.aeropuertoToGetAeropuerto(savedAeropuerto);
    }

    @Override
    public GetAeropuerto updateAeropuertoById(Long id, SaveAeropuerto saveAeropuerto) {
        Aeropuerto aeropuertoFromDb = aeropuertoRepository.findById(id)
                .orElseThrow(()->new AeropuertoNotFoundException("aeropuerto id: "+id+" not found"));

        aeropuertoFromDb.setNombre(saveAeropuerto.nombre());
        aeropuertoFromDb.setCiudad(saveAeropuerto.ciudad());
        aeropuertoFromDb.setPais(saveAeropuerto.pais());

        Aeropuerto aeropuertoSaved = aeropuertoRepository.save(aeropuertoFromDb);

        return AeroPuertoMapper.INSTANCE.aeropuertoToGetAeropuerto(aeropuertoSaved);
    }

    @Override
    public void deleteAeropuertoById(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}

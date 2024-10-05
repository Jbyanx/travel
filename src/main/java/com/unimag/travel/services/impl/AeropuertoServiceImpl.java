package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;
import com.unimag.travel.entities.Aeropuerto;
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
    public Optional<GetAeropuerto> getAeropuertoById(Long id) {
        Optional<Aeropuerto> aeropuertoOptional = aeropuertoRepository.findById(id);
        return aeropuertoOptional.map(AeroPuertoMapper.INSTANCE::aeropuertoToGetAeropuerto);
    }

    @Override
    public Optional<GetAeropuerto> getAeropuertoByName(String name) {
        return aeropuertoRepository.getAeropuertoByNombre(name).map(AeroPuertoMapper.INSTANCE::aeropuertoToGetAeropuerto);
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
        Aeropuerto aeropuertoFromDb = aeropuertoRepository.findById(id).get();
        if(aeropuertoFromDb != null) {
            aeropuertoFromDb.setNombre(saveAeropuerto.nombre());
            aeropuertoFromDb.setCiudad(saveAeropuerto.ciudad());
            aeropuertoFromDb.setPais(saveAeropuerto.pais());
        } else {
            throw new RuntimeException("Aeropuerto no encontrado");
        }
        Aeropuerto aeropuertoSaved = aeropuertoRepository.save(aeropuertoFromDb);

        return AeroPuertoMapper.INSTANCE.aeropuertoToGetAeropuerto(aeropuertoSaved);
    }

    @Override
    public void deleteAeropuertoById(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}

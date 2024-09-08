package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.repositories.AerolineaRepository;
import com.unimag.travel.repositories.AeropuertoRepository;
import com.unimag.travel.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {
    private AeropuertoRepository aeropuertoRepository;

    @Autowired
    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public Optional<Aeropuerto> getAeropuertoById(Long id) {
        return aeropuertoRepository.findById(id);
    }

    @Override
    public Optional<Aeropuerto> getAeropuertoByName(String name) {
        return aeropuertoRepository.getAeropuertoByName(name);
    }

    @Override
    public List<Aeropuerto> getAllAeropuertos() {
        return aeropuertoRepository.findAll();
    }

    @Override
    public Aeropuerto saveAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }

    @Override
    public Aeropuerto updateAeropuertoById(Long id, Aeropuerto saveAeropuerto) {
        Aeropuerto aeropuertoFromDb = aeropuertoRepository.findById(id).get();
        if(aeropuertoFromDb != null) {
            aeropuertoFromDb.setNombre(saveAeropuerto.getNombre());
            aeropuertoFromDb.setCiudad(saveAeropuerto.getCiudad());
            aeropuertoFromDb.setPais(saveAeropuerto.getPais());
            aeropuertoFromDb.setVuelosOrigen(saveAeropuerto.getVuelosOrigen());
            aeropuertoFromDb.setVuelosDestino(saveAeropuerto.getVuelosDestino());
        } else {
            throw new RuntimeException("Aeropuerto no encontrado");
        }
        return aeropuertoRepository.save(aeropuertoFromDb);
    }

    @Override
    public void deleteAeropuertoById(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}

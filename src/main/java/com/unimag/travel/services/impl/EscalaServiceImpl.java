package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Escala;
import com.unimag.travel.repositories.EscalaRepository;
import com.unimag.travel.services.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscalaServiceImpl implements EscalaService {
    private EscalaRepository escalaRepository;

    @Autowired
    public EscalaServiceImpl(EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
    }


    @Override
    public Optional<Escala> getEscalaById(Long id) {
        return escalaRepository.findById(id);
    }


    @Override
    public List<Escala> getAllEscalas() {
        return escalaRepository.findAll();
    }

    @Override
    public Escala saveEscala(Escala escala) {
        return escalaRepository.save(escala);
    }

    @Override
    public Escala updateEscalaById(Long id, Escala saveEscala) {
        Escala escalaFromDb = escalaRepository.findById(id).get();
        if(escalaFromDb != null) {
            escalaFromDb.setTiempoDeEscala(saveEscala.getTiempoDeEscala());
            escalaFromDb.setAeropuerto(saveEscala.getAeropuerto());
            escalaFromDb.setVuelo(saveEscala.getVuelo());
        }else{
            throw new RuntimeException("Escala not found");
        }
        return escalaRepository.save(escalaFromDb);
    }

    @Override
    public void deleteEscalaById(Long id) {
        escalaRepository.deleteById(id);
    }
}

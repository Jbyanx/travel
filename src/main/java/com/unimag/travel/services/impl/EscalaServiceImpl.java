package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Escala;
import com.unimag.travel.exception.EscalaNotFoundException;
import com.unimag.travel.mapper.EscalaMapper;
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
    public GetEscala getEscalaById(Long id) {
        Escala escala = escalaRepository.findById(id)
                .orElseThrow(() -> new EscalaNotFoundException("escala id:"+id+" not found"));
        return EscalaMapper.INSTANCE.escalaToGetEscala(escala);
    }


    @Override
    public List<GetEscala> getAllEscalas() {
        List<Escala> escalas = escalaRepository.findAll();
        return EscalaMapper.INSTANCE.escalaListToGetEscalaList(escalas);
    }

    @Override
    public GetEscala saveEscala(SaveEscala saveEscala) {
        Escala escalaToSave = EscalaMapper.INSTANCE.saveEscalaToEscala(saveEscala);
        Escala escalaSaved = escalaRepository.save(escalaToSave);
        return EscalaMapper.INSTANCE.escalaToGetEscala(escalaRepository.save(escalaSaved));
    }

    @Override
    public GetEscala updateEscalaById(Long id, SaveEscala saveEscala) {
        Escala escalaFromDb = escalaRepository.findById(id)
                .orElseThrow(() -> new EscalaNotFoundException("escala id:"+id+" not found"));

        escalaFromDb.setTiempoDeEscala(saveEscala.duracion());
        escalaFromDb.getAeropuerto().setIdAeropuerto(saveEscala.idAeropuerto());
        escalaFromDb.getVuelo().setIdVuelo(saveEscala.idVuelo());

        return EscalaMapper.INSTANCE.escalaToGetEscala(escalaRepository.save(escalaFromDb));
    }

    @Override
    public void deleteEscalaById(Long id) {
        escalaRepository.deleteById(id);
    }
}

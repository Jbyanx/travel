package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.entities.Escala;
import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.exception.AeropuertoNotFoundException;
import com.unimag.travel.exception.EscalaNotFoundException;
import com.unimag.travel.exception.VueloNotFoundException;
import com.unimag.travel.mapper.EscalaMapper;
import com.unimag.travel.repositories.AeropuertoRepository;
import com.unimag.travel.repositories.EscalaRepository;
import com.unimag.travel.repositories.VueloRepository;
import com.unimag.travel.services.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscalaServiceImpl implements EscalaService {
    private EscalaRepository escalaRepository;
    private AeropuertoRepository aeropuertoRepository;
    private VueloRepository vueloRepository;

    @Autowired
    public EscalaServiceImpl(VueloRepository vueloRepository, AeropuertoRepository aeropuertoRepository, EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
        this.aeropuertoRepository = aeropuertoRepository;
        this.vueloRepository = vueloRepository;
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

        Aeropuerto aeropuerto = aeropuertoRepository.findById(saveEscala.idAeropuerto())
                        .orElseThrow( () -> new AeropuertoNotFoundException("aeropuerto no encontrado al guardar la escala"));

        Vuelo vuelo = vueloRepository.findById(saveEscala.idVuelo())
                        .orElseThrow(() -> new VueloNotFoundException("vuelo no encontrado al guardar escala"));

        escalaToSave.setAeropuerto(aeropuerto);
        escalaToSave.setVuelo(vuelo);

        Escala escalaSaved = escalaRepository.save(escalaToSave);
        return EscalaMapper.INSTANCE.escalaToGetEscala(escalaRepository.save(escalaSaved));
    }

    @Override
    public GetEscala updateEscalaById(Long id, SaveEscala saveEscala) {
        Escala escalaFromDb = escalaRepository.findById(id)
                .orElseThrow(() -> new EscalaNotFoundException("escala id:"+id+" not found"));

        Aeropuerto aeropuerto = aeropuertoRepository.findById(saveEscala.idAeropuerto())
                .orElseThrow( () -> new AeropuertoNotFoundException("aeropuerto no encontrado al guardar la escala"));

        Vuelo vuelo = vueloRepository.findById(saveEscala.idVuelo())
                .orElseThrow(() -> new VueloNotFoundException("vuelo no encontrado al guardar escala"));

        escalaFromDb.setTiempoDeEscala(saveEscala.duracion());
        escalaFromDb.getAeropuerto().setIdAeropuerto(saveEscala.idAeropuerto());
        escalaFromDb.getVuelo().setIdVuelo(saveEscala.idVuelo());
        escalaFromDb.setVuelo(vuelo);
        escalaFromDb.setAeropuerto(aeropuerto);

        return EscalaMapper.INSTANCE.escalaToGetEscala(escalaRepository.save(escalaFromDb));
    }

    @Override
    public void deleteEscalaById(Long id) {
        escalaRepository.deleteById(id);
    }
}

package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.repositories.VueloRepository;
import com.unimag.travel.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    private VueloRepository vueloRepository;

    @Autowired
    public VueloServiceImpl(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }


    @Override
    public Optional<Vuelo> getVueloById(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public Optional<Vuelo> getVueloByName(String name) {
        return vueloRepository.getVueloByName(name);
    }

    @Override
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public Vuelo saveVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Vuelo updateVueloById(Long id, Vuelo saveVuelo) {
        Vuelo vueloFromDb = vueloRepository.findById(id).get();
        if (vueloFromDb != null) {
            vueloFromDb.setAerolinea(vueloFromDb.getAerolinea());
            vueloFromDb.setDestino(vueloFromDb.getDestino());
            vueloFromDb.setDuracion(vueloFromDb.getDuracion());
            vueloFromDb.setCapacidad(vueloFromDb.getCapacidad());
            vueloFromDb.setAeropuertoDestino(vueloFromDb.getAeropuertoDestino());
            vueloFromDb.setAeropuertoOrigen(vueloFromDb.getAeropuertoOrigen());
            vueloFromDb.setEscalas(vueloFromDb.getEscalas());
            vueloFromDb.setFechaDeSalida(vueloFromDb.getFechaDeSalida());
            vueloFromDb.setHoraDeSalida(vueloFromDb.getHoraDeSalida());
            vueloFromDb.setOrigen(vueloFromDb.getOrigen());
        } else{
            throw new RuntimeException("Vuelo no encontrado");
        }
        return vueloRepository.save(saveVuelo);
    }

    @Override
    public void deleteVueloById(Long id) {
        vueloRepository.deleteById(id);
    }
}

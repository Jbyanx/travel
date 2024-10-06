package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.mapper.VueloMapper;
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
    public Optional<GetVuelo> getVueloById(Long id) {
        Optional<Vuelo> vueloOptional = vueloRepository.findById(id);
        return vueloOptional.map(VueloMapper.INSTANCE::vueloToGetVuelo);
    }

    @Override
    public List<GetVuelo> getAllVuelos() {
        List<Vuelo> vueloList = vueloRepository.findAll();
        return VueloMapper.INSTANCE.vueloListToGetVueloList(vueloList);
    }

    @Override
    public GetVuelo saveVuelo(SaveVuelo saveVuelo) {
        Vuelo vueloToSave = VueloMapper.INSTANCE.saveVueloToVuelo(saveVuelo);
        Vuelo vueloSaved = vueloRepository.save(vueloToSave);
        return VueloMapper.INSTANCE.vueloToGetVuelo(vueloSaved);
    }

    @Override
    public GetVuelo updateVueloById(Long id, SaveVuelo saveVuelo) {
        Vuelo vueloFromDb = vueloRepository.findById(id).get();
        if (vueloFromDb != null) {
            vueloFromDb.getAerolinea().setIdAerolinea(saveVuelo.idAerolinea());
            vueloFromDb.setDestino(saveVuelo.destino());
            vueloFromDb.setDuracion(saveVuelo.duracion());
            vueloFromDb.setCapacidad(saveVuelo.capacidad());
            vueloFromDb.getAeropuertoDestino().setIdAeropuerto(saveVuelo.idAeropuertoDestino());
            vueloFromDb.getAeropuertoOrigen().setIdAeropuerto(saveVuelo.idAeropuertoOrigen());
            vueloFromDb.setFechaDeSalida(saveVuelo.fechaDeSalida());
            vueloFromDb.setHoraDeSalida(saveVuelo.horaDeSalida());
            vueloFromDb.setOrigen(saveVuelo.origen());
        } else{
            throw new RuntimeException("Vuelo no encontrado");
        }
        Vuelo vueloSaved = vueloRepository.save(vueloFromDb);
        return VueloMapper.INSTANCE.vueloToGetVuelo(vueloSaved);
    }

    @Override
    public void deleteVueloById(Long id) {
        vueloRepository.deleteById(id);
    }
}

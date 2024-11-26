package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.exception.VueloNotFoundException;
import com.unimag.travel.mapper.VueloMapper;
import com.unimag.travel.repositories.AerolineaRepository;
import com.unimag.travel.repositories.AeropuertoRepository;
import com.unimag.travel.repositories.VueloRepository;
import com.unimag.travel.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Override
    public GetVuelo getVueloById(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException("vuelo id:" + id + " not found"));
        return VueloMapper.INSTANCE.vueloToGetVuelo(vuelo);
    }

    @Override
    public List<GetVuelo> getAllVuelos() {
        List<Vuelo> vueloList = vueloRepository.findAll();
        return VueloMapper.INSTANCE.vueloListToGetVueloList(vueloList);
    }

    @Override
    public GetVuelo saveVuelo(SaveVuelo saveVuelo) {
        // Obtener las entidades relacionadas
        Aerolinea aerolinea = aerolineaRepository.findById(saveVuelo.idAerolinea())
                .orElseThrow(() -> new RuntimeException("Aerolinea no encontrada"));
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.findById(saveVuelo.idAeropuertoOrigen())
                .orElseThrow(() -> new RuntimeException("Aeropuerto de origen no encontrado"));
        Aeropuerto aeropuertoDestino = aeropuertoRepository.findById(saveVuelo.idAeropuertoDestino())
                .orElseThrow(() -> new RuntimeException("Aeropuerto de destino no encontrado"));

        // Mapear la entidad Vuelo
        Vuelo vueloToSave = VueloMapper.INSTANCE.saveVueloToVuelo(saveVuelo);
        vueloToSave.setAerolinea(aerolinea);
        vueloToSave.setAeropuertoOrigen(aeropuertoOrigen);
        vueloToSave.setAeropuertoDestino(aeropuertoDestino);

        // Guardar el vuelo
        Vuelo vueloSaved = vueloRepository.save(vueloToSave);
        return VueloMapper.INSTANCE.vueloToGetVuelo(vueloSaved);
    }

    @Override
    public GetVuelo updateVueloById(Long id, SaveVuelo saveVuelo) {
        Vuelo vueloFromDb = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException("vuelo id:" + id + " not found"));

        // Obtener las entidades relacionadas
        Aerolinea aerolinea = aerolineaRepository.findById(saveVuelo.idAerolinea())
                .orElseThrow(() -> new RuntimeException("Aerolinea no encontrada"));
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.findById(saveVuelo.idAeropuertoOrigen())
                .orElseThrow(() -> new RuntimeException("Aeropuerto de origen no encontrado"));
        Aeropuerto aeropuertoDestino = aeropuertoRepository.findById(saveVuelo.idAeropuertoDestino())
                .orElseThrow(() -> new RuntimeException("Aeropuerto de destino no encontrado"));

        // Actualizar el vuelo
        vueloFromDb.setAerolinea(aerolinea);
        vueloFromDb.setDestino(saveVuelo.destino());
        vueloFromDb.setDuracion(saveVuelo.duracion());
        vueloFromDb.setCapacidad(saveVuelo.capacidad());
        vueloFromDb.setAeropuertoDestino(aeropuertoDestino);
        vueloFromDb.setAeropuertoOrigen(aeropuertoOrigen);
        vueloFromDb.setFechaDeSalida(saveVuelo.fechaDeSalida());
        vueloFromDb.setHoraDeSalida(saveVuelo.horaDeSalida());
        vueloFromDb.setOrigen(saveVuelo.origen());

        // Guardar el vuelo actualizado
        Vuelo vueloSaved = vueloRepository.save(vueloFromDb);
        return VueloMapper.INSTANCE.vueloToGetVuelo(vueloSaved);
    }

    @Override
    public void deleteVueloById(Long id) {
        vueloRepository.deleteById(id);
    }
}

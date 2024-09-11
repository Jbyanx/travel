package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.repositories.AerolineaRepository;
import com.unimag.travel.services.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {
    private AerolineaRepository aerolineaRepository;

    @Autowired
    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }


    @Override
    public Optional<Aerolinea> getAerolineaById(Long id) {
        return aerolineaRepository.findById(id);
    }

    @Override
    public Optional<Aerolinea> getAerolineaByName(String name) {
        return aerolineaRepository.getAerolineaByNombre(name);
    }

    @Override
    public List<Aerolinea> getAllAerolineas() {
        return aerolineaRepository.findAll();
    }

    @Override
    public Aerolinea saveAerolinea(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    public Aerolinea updateAerolineaById(Long id, Aerolinea saveAerolinea) {
        Aerolinea aerolineaFromDb = aerolineaRepository.findById(id).get();
        if(aerolineaFromDb != null) {
            aerolineaFromDb.setNombre(saveAerolinea.getNombre());
            aerolineaFromDb.setPaisDeOrigen(saveAerolinea.getPaisDeOrigen());
            aerolineaFromDb.setVuelos(saveAerolinea.getVuelos());
            aerolineaFromDb.setCodigoDeAerolinea(saveAerolinea.getCodigoDeAerolinea());
        } else {
            throw new RuntimeException("Aerolinea ID:" +id + " not found");
        }
        return aerolineaRepository.save(aerolineaFromDb);
    }

    @Override
    public void deleteAerolineaById(Long id) {
        aerolineaRepository.deleteById(id);
    }
}

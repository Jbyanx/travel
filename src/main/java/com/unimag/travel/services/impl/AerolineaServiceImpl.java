package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.exception.AerolineaNotFoundException;
import com.unimag.travel.mapper.AerolineaMapper;
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
    public GetAerolinea getAerolineaById(Long id) {
        Aerolinea aerolinea = aerolineaRepository.findById(id)
                .orElseThrow(() -> new AerolineaNotFoundException("Aerolinea "+id+" not found"));
        return AerolineaMapper.INSTANCE.aerolineaToGetAerolinea(aerolinea);
    }

    @Override
    public GetAerolinea getAerolineaByName(String name) {
        Aerolinea aerolinea = aerolineaRepository.getAerolineaByNombre(name)
                .orElseThrow(() -> new AerolineaNotFoundException("Aerolinea "+name+" not found"));
        return AerolineaMapper.INSTANCE.aerolineaToGetAerolinea(aerolinea);
    }

    @Override
    public List<GetAerolinea> getAllAerolineas() {
        List<Aerolinea> aerolineaList = aerolineaRepository.findAll();
        return AerolineaMapper.INSTANCE.aerolineaListToGetAerolineaList(aerolineaList);
    }

    @Override
    public GetAerolinea saveAerolinea(SaveAerolinea saveAerolinea) {
        //convertimos el saveAerolinea a entidad
        Aerolinea aerolineaToSave = AerolineaMapper.INSTANCE.saveAerolineaToAerolinea(saveAerolinea);
        //guardamos esa entidad y devolvemos el mapeo a get aerolinea
        GetAerolinea getSavedAerolinea = AerolineaMapper.INSTANCE.aerolineaToGetAerolinea(aerolineaRepository.save(aerolineaToSave));
        return getSavedAerolinea;
    }

    @Override
    public GetAerolinea updateAerolineaById(Long id, SaveAerolinea saveAerolinea) {
        Aerolinea newAerolinea = AerolineaMapper.INSTANCE.saveAerolineaToAerolinea(saveAerolinea);
        Aerolinea aerolineaFromDb = aerolineaRepository.findById(id)
                .orElseThrow(() -> new AerolineaNotFoundException("aerolinea "+id+" not found"));

        aerolineaFromDb.setNombre(newAerolinea.getNombre());
        aerolineaFromDb.setPaisDeOrigen(newAerolinea.getPaisDeOrigen());
        aerolineaFromDb.setVuelos(newAerolinea.getVuelos());
        aerolineaFromDb.setCodigoDeAerolinea(newAerolinea.getCodigoDeAerolinea());

        return AerolineaMapper.INSTANCE.aerolineaToGetAerolinea(aerolineaRepository.save(aerolineaFromDb));
    }

    @Override
    public void deleteAerolineaById(Long id) {
        aerolineaRepository.deleteById(id);
    }
}

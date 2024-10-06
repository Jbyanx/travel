package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.entities.Aerolinea;
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
    public Optional<GetAerolinea> getAerolineaById(Long id) {
        Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);
        return aerolineaOptional.map(AerolineaMapper.INSTANCE::aerolineaToGetAerolinea);
    }

    @Override
    public Optional<GetAerolinea> getAerolineaByName(String name) {
        return aerolineaRepository.getAerolineaByNombre(name).map(AerolineaMapper.INSTANCE::aerolineaToGetAerolinea);
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
        Aerolinea aerolineaFromDb = aerolineaRepository.findById(id).get();

        if(aerolineaFromDb != null) {
            aerolineaFromDb.setNombre(newAerolinea.getNombre());
            aerolineaFromDb.setPaisDeOrigen(newAerolinea.getPaisDeOrigen());
            aerolineaFromDb.setVuelos(newAerolinea.getVuelos());
            aerolineaFromDb.setCodigoDeAerolinea(newAerolinea.getCodigoDeAerolinea());
        } else {
            throw new RuntimeException("Aerolinea ID:" +id + " not found");
        }
        return AerolineaMapper.INSTANCE.aerolineaToGetAerolinea(aerolineaRepository.save(aerolineaFromDb));
    }

    @Override
    public void deleteAerolineaById(Long id) {
        aerolineaRepository.deleteById(id);
    }
}

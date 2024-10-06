package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Escala;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AeroPuertoMapper.class, VueloMapper.class})
public interface EscalaMapper {
    EscalaMapper INSTANCE = Mappers.getMapper(EscalaMapper.class);

    //Save Escala
    @Mapping(target = "idVuelo", source = "vuelo.idVuelo")
    @Mapping(target = "idAeropuerto", source = "aeropuerto.idAeropuerto")
    @Mapping(target = "duracion", source = "tiempoDeEscala")
    SaveEscala escalaToSaveEscala(Escala escala);

    @InheritConfiguration
    Escala saveEscalaToEscala(SaveEscala saveEscala);

    List<SaveEscala> escalaListToSaveEscalaList(List<Escala> escalaList);
    List<Escala> saveEscalaListToEscalaList(List<SaveEscala> saveEscalaList);

    //Get Escala
    @Mapping(target = "id", source = "idEscala")
    @Mapping(target = "duracion", source = "tiempoDeEscala")
    GetEscala escalaToGetEscala(Escala escala);

    @InheritConfiguration
    Escala getEscalaToEscala(GetEscala getEscala);

    List<GetEscala> escalaListToGetEscalaList(List<Escala> escalaList);
    List<Escala> getEscalaListToEscalaList(List<GetEscala> getEscalaList);
}

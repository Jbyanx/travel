package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Escala;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AeroPuertoMapper.class, VueloMapper.class})
public interface EscalaMapper {
    //Save Escala
    @Mapping(target = "idVuelo", source = "vuelo.idVuelo")
    @Mapping(target = "idAeropuerto", source = "aeropuerto.idAeropuerto")
    @Mapping(target = "duracion", source = "tiempoDeEscala")
    SaveEscala escalaToSaveEscala(Escala escala);

    @InheritConfiguration
    Escala saveEscalaToEscala(SaveEscala saveEscala);

    //Get Escala
    @Mapping(target = "id", source = "idEscala")
    @Mapping(target = "duracion", source = "tiempoDeEscala")
    GetEscala escalaToGetEscala(Escala escala);

    @InheritConfiguration
    Escala getEscalaToEscala(GetEscala getEscala);
}

package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Vuelo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface VueloMapper {
    // SAVE VUELO
    @Mapping(target = "idAerolinea", source = "aerolinea.idAerolinea")
    @Mapping(target = "idAeropuertoOrigen", source = "aeropuertoOrigen.idAeropuerto")
    @Mapping(target = "idAeropuertoDestino", source = "aeropuertoDestino.idAeropuerto")
    SaveVuelo vueloToSaveVuelo(Vuelo vuelo);

    @InheritInverseConfiguration
    Vuelo saveVueloToVuelo(SaveVuelo saveVuelo);

    // GET VUELO
    @Mapping(target = "id", source = "idVuelo")
    @Mapping(target = "aerolinea", source = "aerolinea.nombre")
    @Mapping(target = "aeropuertoOrigen", source = "aeropuertoOrigen.nombre")
    @Mapping(target = "aeropuertoDestino", source = "aeropuertoDestino.nombre")
    GetVuelo vueloToGetVuelo(Vuelo vuelo);

    @InheritInverseConfiguration
    Vuelo getVueloToVuelo(GetVuelo getVuelo);
}

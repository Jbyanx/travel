package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VueloMapper {
    //Instancia estatica
    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    // SAVE VUELO
    @Mapping(target = "idAerolinea", source = "aerolinea.idAerolinea")
    @Mapping(target = "idAeropuertoOrigen", source = "aeropuertoOrigen.idAeropuerto")
    @Mapping(target = "idAeropuertoDestino", source = "aeropuertoDestino.idAeropuerto")
    SaveVuelo vueloToSaveVuelo(Vuelo vuelo);

    @Mapping(source = "idAerolinea", target = "aerolinea.idAerolinea")
    @Mapping(source = "idAeropuertoOrigen", target = "aeropuertoOrigen.idAeropuerto")
    @Mapping(source = "idAeropuertoDestino", target = "aeropuertoDestino.idAeropuerto")
    Vuelo saveVueloToVuelo(SaveVuelo saveVuelo);

    List<SaveVuelo> vueloListToSaveVueloList(List<Vuelo> vueloList);
    List<Vuelo> saveVueloListToVueloList(List<SaveVuelo> saveVueloList);

    // GET VUELO
    @Mapping(target = "id", source = "idVuelo")
    @Mapping(target = "aerolinea", source = "aerolinea.nombre")
    @Mapping(target = "aeropuertoOrigen", source = "aeropuertoOrigen.nombre")
    @Mapping(target = "aeropuertoDestino", source = "aeropuertoDestino.nombre")
    GetVuelo vueloToGetVuelo(Vuelo vuelo);

    @Mapping(source = "id", target = "idVuelo")
    @Mapping(source = "aerolinea", target = "aerolinea.nombre")
    @Mapping(source = "aeropuertoOrigen", target = "aeropuertoOrigen.nombre")
    @Mapping(source = "aeropuertoDestino", target = "aeropuertoDestino.nombre")
    Vuelo getVueloToVuelo(GetVuelo getVuelo);

    List<GetVuelo> vueloListToGetVueloList(List<Vuelo> vueloList);
    List<Vuelo> getVueloListToVueloList(List<GetVuelo> getVueloList);
}

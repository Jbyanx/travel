package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;
import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.entities.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {VueloMapper.class})
public interface AeroPuertoMapper {
    //Instancia estatica
    AeroPuertoMapper INSTANCE = Mappers.getMapper(AeroPuertoMapper.class);

    //GET AEROPUERTO
    @Mapping(target = "id", source = "idAeropuerto")
    GetAeropuerto aeropuertoToGetAeropuerto(Aeropuerto aeropuerto);

    @Mapping(source = "id", target = "idAeropuerto")
    Aeropuerto getAeropuertoToAeropuerto(GetAeropuerto getAeropuerto);

    List<GetAeropuerto> aeropuertoLisToGetAeropuertoList(List<Aeropuerto> aeropuertoList);
    List<Aeropuerto> getAeropuertoListToAeropuertoList(List<GetAeropuerto> getAeropuertoList);

    //SAVE AEROPUERTO
    SaveAeropuerto aeropuertoToSaveAeropuerto(Aeropuerto aeropuerto);

    Aeropuerto saveAeropuertoToAeropuerto(SaveAeropuerto saveAeropuerto);

    List<SaveAeropuerto> aeropuertoListToSaveAeropuertoList(List<Aeropuerto> aeropuertoList);
    List<Aeropuerto> saveAeropuertoListToAeropuertoList(List<SaveAeropuerto> saveAeropuertoList);

}

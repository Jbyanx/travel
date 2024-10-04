package com.unimag.travel.mapper;

import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.entities.Escala;
import com.unimag.travel.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AerolineaMapper {

    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    //SAVE AEROLINEA
    @Mappings({
            @Mapping(target = "codigo", source = "codigoDeAerolinea"),
            @Mapping(target = "pais", source = "paisDeOrigen")
    })
    SaveAerolinea aerolineaToSaveAerolinea(Aerolinea aerolinea);

    @Mappings({
            @Mapping(target = "codigoDeAerolinea" ,source = "codigo"),
            @Mapping(target = "paisDeOrigen" , source = "pais"),
    })
    Aerolinea SaveAerolineaToAerolinea(SaveAerolinea saveAerolinea);

    List<Aerolinea> saveAerolineaListToAerolineaList(List<SaveAerolinea> listSaveAerolinea);

    List<SaveAerolinea> aerolineaListToSaveAerolineaList(List<Aerolinea> listAerolinea);

    //GET AEROLINEA
    @Mappings({
            @Mapping(target ="codigo", source = "codigoDeAerolinea"),
            @Mapping(target = "pais", source = "paisDeOrigen"),
            @Mapping(target = "id", source = "idAerolinea")
    })
    GetAerolinea aerolineaToGetAerolinea(Aerolinea aerolinea);

    @Mappings({
            @Mapping(target ="codigoDeAerolinea", source = "codigo"),
            @Mapping(target = "paisDeOrigen", source = "pais"),
            @Mapping(target = "idAerolinea", source = "id")
    })
    Aerolinea GetAerolineaToAerolinea(GetAerolinea getAerolinea);

    @Mappings({
            @Mapping(target ="id", source = "idVuelo"),
            @Mapping(target = "aeropuertoOrigen", source = "aeropuertoOrigen.nombre"),
            @Mapping(target = "aeropuertoDestino", source = "aeropuertoDestino.nombre")
    })
    GetVuelo vueloToGetVuelo(Vuelo vuelo);

    @Mappings({
            @Mapping(target = "idVuelo", source = "id"),
            @Mapping(target = "aeropuertoOrigen.nombre", source = "aeropuertoOrigen"),
            @Mapping(target = "aeropuertoDestino.nombre", source = "aeropuertoDestino")
    })
    Vuelo getVueloToVuelo(GetVuelo getVuelo);

    //listas de vuelos de la aerolinea
    List<GetVuelo> vueloListToGetVueloList(List<Vuelo> vuelos);

    List<Vuelo> getVueloListToVueloList(List<GetVuelo> getVuelos);

    //listas de aerolineas
    List<GetAerolinea> aerolineaListToGetAerolineaList(List<Aerolinea> aerolineas);

    List<Aerolinea> getAerolineaListToAerolineaList(List<GetAerolinea> aerolineas);

    //escalas dentro de GetVuelos
    @Mapping(target = "aeropuerto", source = "aeropuerto.nombre")
    GetEscala escalaToGetEscala(Escala escala);

    @Mapping(target = "aeropuerto.nombre", source = "aeropuerto")
    Escala getEscalaToEscala(GetEscala getEscala);

    //listas de escalas
    List<GetEscala> escalaListToGetEscalaList(List<Escala> escalas);

    List<Escala> getEscalaListToEscalaList(List<GetEscala> getEscalaList);
}

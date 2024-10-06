package com.unimag.travel.mapper;

import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.entities.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {VueloMapper.class})
public interface AerolineaMapper {

    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    //SAVE AEROLINEA
    @Mapping(target = "codigo", source = "codigoDeAerolinea")
    @Mapping(target = "pais", source = "paisDeOrigen")
    SaveAerolinea aerolineaToSaveAerolinea(Aerolinea aerolinea);

    @Mapping(source = "codigo", target = "codigoDeAerolinea")
    @Mapping(source = "pais", target = "paisDeOrigen")
    Aerolinea saveAerolineaToAerolinea(SaveAerolinea saveAerolinea);

    List<SaveAerolinea> aerolineaListToSaveAerolineaList(List<Aerolinea> aerolineaList);
    List<Aerolinea> saveAerolineaListToAerolineaList(List<SaveAerolinea> saveAerolineaList);

    //GET AEROLINEA
    @Mapping(target = "id", source = "idAerolinea")
    @Mapping(target ="codigo", source = "codigoDeAerolinea")
    @Mapping(target = "pais", source = "paisDeOrigen")
    GetAerolinea aerolineaToGetAerolinea(Aerolinea aerolinea);

    @Mapping(source = "id", target = "idAerolinea")
    @Mapping(source ="codigo", target = "codigoDeAerolinea")
    @Mapping(source = "pais", target = "paisDeOrigen")
    Aerolinea GetAerolineaToAerolinea(GetAerolinea getAerolinea);

    List<GetAerolinea> aerolineaListToGetAerolineaList(List<Aerolinea> aerolineaList);
    List<Aerolinea> getAerolineaListToAerolineaList(List<GetAerolinea> getAerolineaList);
}

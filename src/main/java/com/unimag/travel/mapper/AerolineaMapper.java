package com.unimag.travel.mapper;

import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.entities.Vuelo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AerolineaMapper {

    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    // SAVE AEROLINEA
    @Mapping(target = "codigoDeAerolinea", source = "codigo")
    @Mapping(target = "paisDeOrigen", source = "pais")
    Aerolinea saveAerolineaToAerolinea(SaveAerolinea saveAerolinea);

    @InheritInverseConfiguration
    SaveAerolinea aerolineaToSaveAerolinea(Aerolinea aerolinea);

    List<Aerolinea> saveAerolineaListToAerolineaList(List<SaveAerolinea> saveAerolineaList);
    List<SaveAerolinea> aerolineaListToSaveAerolineaList(List<Aerolinea> aerolineaList);

    // GET AEROLINEA
    @Mapping(target = "id", source = "idAerolinea")
    @Mapping(target = "codigo", source = "codigoDeAerolinea")
    @Mapping(target = "pais", source = "paisDeOrigen")
    GetAerolinea aerolineaToGetAerolinea(Aerolinea aerolinea);

    @InheritInverseConfiguration
    Aerolinea getAerolineaToAerolinea(GetAerolinea getAerolinea);

    List<GetAerolinea> aerolineaListToGetAerolineaList(List<Aerolinea> aerolineaList);
    List<Aerolinea> getAerolineaListToAerolineaList(List<GetAerolinea> getAerolineaList);
}

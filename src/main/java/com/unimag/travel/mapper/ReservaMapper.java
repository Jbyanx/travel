package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;
import com.unimag.travel.entities.Reserva;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ClienteMapper.class, VueloMapper.class})
public interface ReservaMapper {
    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    @Mapping(target = "idCliente", source = "cliente.idCliente")
    @Mapping(target = "idVuelo", source = "vuelo.idVuelo")
    SaveReserva reservaToSaveReserva(Reserva reserva);

    @InheritInverseConfiguration
    Reserva saveReservaToReserva(SaveReserva saveReserva);

    @Mapping(source = "idReserva", target = "id")
    GetReserva reservaToGetReserva(Reserva reserva);

    @InheritInverseConfiguration
    Reserva getReservaToReserva(GetReserva getReserva);
}
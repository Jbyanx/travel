package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;
import com.unimag.travel.entities.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {VueloMapper.class, ReservaMapper.class})
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    SaveCliente clienteToSaveCliente(Cliente cliente);

    @InheritInverseConfiguration
    Cliente saveClienteToCliente(SaveCliente saveCliente);

    @Mapping(source = "idCliente", target = "id")
    GetCliente clienteToGetCliente(Cliente cliente);

    @InheritInverseConfiguration
    Cliente getClienteToSaveCliente(GetCliente getCliente);
}

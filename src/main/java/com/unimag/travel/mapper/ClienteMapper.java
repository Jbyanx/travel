package com.unimag.travel.mapper;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;
import com.unimag.travel.entities.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {VueloMapper.class, ReservaMapper.class})
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    SaveCliente clienteToSaveCliente(Cliente cliente);

    @InheritInverseConfiguration
    Cliente saveClienteToCliente(SaveCliente saveCliente);

    List<SaveCliente> clienteListToSaveClienteList(List<Cliente> clienteList);
    List<Cliente> saveClienteListToClienteList(List<SaveCliente> saveClienteList);

    @Mapping(source = "idCliente", target = "id")
    GetCliente clienteToGetCliente(Cliente cliente);

    @InheritInverseConfiguration
    Cliente getClienteToCliente(GetCliente getCliente);

    List<GetCliente> clienteListToGetClienteList(List<Cliente> clienteList);
    List<Cliente> getClienteListToGetClienteList(List<GetCliente> getClienteList);
}

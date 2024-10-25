package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    GetCliente getClienteById(Long id);

    GetCliente getClienteByName(String name);

    List<GetCliente> getAllClientes();

    GetCliente saveCliente(SaveCliente saveCliente);

    GetCliente updateClienteById(Long id, SaveCliente saveCliente);

    void deleteClienteById(Long id);
}

package com.unimag.travel.services;

import com.unimag.travel.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<Cliente> getClienteById(Long id);

    Optional<Cliente> getClienteByName(String name);

    List<Cliente> getAllClientes();

    Cliente saveCliente(Cliente cliente);

    Cliente updateClienteById(Long id, Cliente saveCliente);

    void deleteClienteById(Long id);
}

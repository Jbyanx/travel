package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Cliente;
import com.unimag.travel.repositories.ClienteRepository;
import com.unimag.travel.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> getClienteByName(String name) {
        return clienteRepository.getClienteByName(name);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateClienteById(Long id, Cliente saveCliente) {
        Cliente clienteFromDb = clienteRepository.findById(id).get();
        if (clienteFromDb != null) {
            clienteFromDb.setNombre(saveCliente.getNombre());
            clienteFromDb.setApellido(saveCliente.getApellido());
            clienteFromDb.setVuelos(saveCliente.getVuelos());
            clienteFromDb.setDireccion(saveCliente.getDireccion());
            clienteFromDb.setTelefono(saveCliente.getTelefono());
            clienteFromDb.setCorreoElectronico(saveCliente.getCorreoElectronico());
            clienteFromDb.setReservas(saveCliente.getReservas());
        } else{
            throw new RuntimeException("Cliente no encontrado");
        }
        return clienteRepository.save(clienteFromDb);
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }
}

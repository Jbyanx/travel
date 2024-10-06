package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.mapper.ClienteMapper;
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
    public Optional<GetCliente> getClienteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(ClienteMapper.INSTANCE::clienteToGetCliente);
    }

    @Override
    public Optional<GetCliente> getClienteByName(String name) {
        return clienteRepository.getClienteByNombre(name).map(ClienteMapper.INSTANCE::clienteToGetCliente);
    }

    @Override
    public List<GetCliente> getAllClientes() {
        List<Cliente> clienteList = clienteRepository.findAll();
        return ClienteMapper.INSTANCE.clienteListToGetClienteList(clienteList);
    }

    @Override
    public GetCliente saveCliente(SaveCliente saveCliente) {
        Cliente clienteToSave = ClienteMapper.INSTANCE.saveClienteToCliente(saveCliente);
        Cliente savedCliente = clienteRepository.save(clienteToSave);
        return ClienteMapper.INSTANCE.clienteToGetCliente(savedCliente);
    }

    @Override
    public GetCliente updateClienteById(Long id, SaveCliente saveCliente) {
        Cliente clienteFromDb = clienteRepository.findById(id).get();
        if (clienteFromDb != null) {
            clienteFromDb.setNombre(saveCliente.nombre());
            clienteFromDb.setApellido(saveCliente.apellido());
            clienteFromDb.setDireccion(saveCliente.direccion());
            clienteFromDb.setTelefono(saveCliente.telefono());
            clienteFromDb.setCorreoElectronico(saveCliente.correoElectronico());
        } else{
            throw new RuntimeException("Cliente no encontrado");
        }
        return ClienteMapper.INSTANCE.clienteToGetCliente(clienteRepository.save(clienteFromDb));
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }
}

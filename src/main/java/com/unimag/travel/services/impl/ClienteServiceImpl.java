package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.exception.ClienteNotFoundException;
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
    public GetCliente getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con id: "+ id +" no encontrado"));
        return ClienteMapper.INSTANCE.clienteToGetCliente(cliente);
    }

    @Override
    public GetCliente getClienteByName(String name) {
        Cliente cliente = clienteRepository.getClienteByNombre(name)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con nombre: "+ name +" no encontrado"));
        return ClienteMapper.INSTANCE.clienteToGetCliente(cliente);
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
        Cliente clienteFromDb = clienteRepository.findById(id)
                .orElseThrow(()->new ClienteNotFoundException("Cliente con id: "+id+" no encontrado"));
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

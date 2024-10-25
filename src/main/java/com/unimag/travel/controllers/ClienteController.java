package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveCliente;
import com.unimag.travel.dto.response.GetCliente;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.exception.ClienteNotFoundException;
import com.unimag.travel.mapper.ClienteMapper;
import com.unimag.travel.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<GetCliente>> getAllClientes() {
        List<GetCliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<GetCliente> getClienteById(@PathVariable Long idCliente) {
         GetCliente getCliente = clienteService.getClienteById(idCliente);
         return ResponseEntity.ok(getCliente);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<GetCliente> updateCliente(@PathVariable Long idCliente, @RequestBody SaveCliente saveCliente) {
        try {
            GetCliente clienteFromDb = clienteService.updateClienteById(idCliente, saveCliente);

            return ResponseEntity.ok(clienteFromDb);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long idCliente) {
        clienteService.deleteClienteById(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    private ResponseEntity<GetCliente> crearCliente(@RequestBody SaveCliente saveCliente) {
        Cliente newCliente = ClienteMapper.INSTANCE.getClienteToCliente(clienteService.saveCliente(saveCliente));
        //URI newLocation = URI.create("/clientes/"+newCliente.getIdCliente());

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.getIdCliente())
                .toUri();

        GetCliente clienteSaved = ClienteMapper.INSTANCE.clienteToGetCliente(newCliente);
        return ResponseEntity.created(newLocation).body(clienteSaved);
    }
}

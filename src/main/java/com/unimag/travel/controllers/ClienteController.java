package com.unimag.travel.controllers;

import com.unimag.travel.entities.Cliente;
import com.unimag.travel.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();

        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long idCliente) {
        return clienteService.getClienteById(idCliente) //transformamos el cliente en responseEntity con map
                .map(ResponseEntity::ok)  // Si el cliente existe, retorna 200 OK con el cliente.
                .orElse(ResponseEntity.notFound().build());  // Si el cliente no existe, retorna 404 Not Found.
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return crearCliente(cliente);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long idCliente, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteFromDb = Optional.of(clienteService.updateClienteById(idCliente, cliente));

        return clienteFromDb.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return crearCliente(cliente);
                });
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long idCliente) {
        clienteService.deleteClienteById(idCliente);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Cliente> crearCliente(Cliente cliente) {
        Cliente newCliente = clienteService.saveCliente(cliente);
        //URI newLocation = URI.create("/clientes/"+newCliente.getIdCliente());

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.getIdCliente())
                .toUri();

        return ResponseEntity.created(newLocation).body(newCliente);
    }
}

package com.unimag.travel.controllers;

import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    private VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {this.vueloService = vueloService;}

    @GetMapping
    public ResponseEntity<List<Vuelo>> getAllVuelos() {
        List<Vuelo> vuelos = vueloService.getAllVuelos();

        return ResponseEntity.ok(vuelos);
    }

    @GetMapping("/{idVuelo}")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable Long idVuelo) {
        return vueloService.getVueloById(idVuelo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long idVuelo, @RequestBody Vuelo vuelo) {
        Optional<Vuelo> vueloFromDb = Optional.of(vueloService.updateVueloById(idVuelo, vuelo));

        return vueloFromDb.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idVuelo}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long idVuelo) {
        vueloService.deleteVueloById(idVuelo);
        return ResponseEntity.noContent().build();
    }


    private ResponseEntity<Vuelo> crearVuelo(Vuelo vuelo) {
        Vuelo newVuelo = vueloService.saveVuelo(vuelo);


        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.getIdVuelo())
                .toUri();

        return ResponseEntity.created(newLocation).body(newVuelo);
    }
}

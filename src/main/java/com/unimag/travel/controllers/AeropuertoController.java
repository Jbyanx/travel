package com.unimag.travel.controllers;

import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aeropuertos")
public class AeropuertoController {
    private AeropuertoService aeropuertoService;

    @Autowired
    public AeropuertoController(AeropuertoService aeropuertoService){this.aeropuertoService=aeropuertoService;}

    @GetMapping
    public ResponseEntity<List<Aeropuerto>> getAeropuertos(){
        List<Aeropuerto> aeropuertos = aeropuertoService.getAllAeropuertos();
        return ResponseEntity.ok(aeropuertos);
    }

    @GetMapping("/{idAeropuetro}")
    public ResponseEntity<Aeropuerto> getAeropuerto(@PathVariable Long idAeropuetro){
        return aeropuertoService.getAeropuertoById(idAeropuetro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/idAeropuerto")
    public ResponseEntity<Aeropuerto> updateAeropuerto(@PathVariable Long idAeropuerto, @RequestBody Aeropuerto aeropuerto){
        Optional<Aeropuerto> aeropuertoFromDb = Optional.of(aeropuertoService.updateAeropuertoById(idAeropuerto, aeropuerto));

        return aeropuertoFromDb.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createAeropuerto(aeropuerto);
                });
    }

    @DeleteMapping("/{iAeropuerto}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long idAeropuerto) {
        aeropuertoService.deleteAeropuertoById(idAeropuerto);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Aeropuerto> crearAeropuerto(Aeropuerto aeropuerto) {
        Aeropuerto newAeropuerto = aeropuertoService.saveAeropuerto(aeropuerto);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAeropuerto.getIdAeropuerto())
                .toUri();

        return ResponseEntity.created(newLocation).body(newAeropuerto);
    }
}

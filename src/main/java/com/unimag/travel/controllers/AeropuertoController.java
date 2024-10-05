package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;
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
    public ResponseEntity<List<GetAeropuerto>> getAeropuertos(){
        List<GetAeropuerto> aeropuertos = aeropuertoService.getAllAeropuertos();
        return ResponseEntity.ok(aeropuertos);
    }

    @GetMapping("/{idAeropuetro}")
    public ResponseEntity<GetAeropuerto> getAeropuerto(@PathVariable Long idAeropuetro){
        return aeropuertoService.getAeropuertoById(idAeropuetro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/idAeropuerto")
    public ResponseEntity<GetAeropuerto> updateAeropuerto(@PathVariable Long idAeropuerto, @RequestBody SaveAeropuerto saveAeropuerto){
        try{
            GetAeropuerto getAeropuerto = aeropuertoService.updateAeropuertoById(idAeropuerto, saveAeropuerto);

            return ResponseEntity.ok(getAeropuerto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{iAeropuerto}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long idAeropuerto) {
        aeropuertoService.deleteAeropuertoById(idAeropuerto);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<GetAeropuerto> createAeropuerto(SaveAeropuerto saveAeropuerto) {
        GetAeropuerto savedAeropuerto = aeropuertoService.saveAeropuerto(saveAeropuerto);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAeropuerto.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(savedAeropuerto);
    }
}

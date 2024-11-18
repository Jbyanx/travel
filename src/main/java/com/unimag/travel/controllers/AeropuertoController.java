package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveAeropuerto;
import com.unimag.travel.dto.response.GetAeropuerto;
import com.unimag.travel.services.AeropuertoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<GetAeropuerto> getAeropuertoById(@PathVariable Long idAeropuetro){
        return ResponseEntity.ok(aeropuertoService.getAeropuertoById(idAeropuetro));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/idAeropuerto")
    public ResponseEntity<GetAeropuerto> updateAeropuerto(@PathVariable Long idAeropuerto, @RequestBody @Valid SaveAeropuerto saveAeropuerto){
        GetAeropuerto getAeropuerto = aeropuertoService.updateAeropuertoById(idAeropuerto, saveAeropuerto);
        return ResponseEntity.ok(getAeropuerto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{iAeropuerto}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long idAeropuerto) {
        aeropuertoService.deleteAeropuertoById(idAeropuerto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    private ResponseEntity<GetAeropuerto> createAeropuerto(@RequestBody @Valid SaveAeropuerto saveAeropuerto) {
        GetAeropuerto savedAeropuerto = aeropuertoService.saveAeropuerto(saveAeropuerto);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAeropuerto.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(savedAeropuerto);
    }
}

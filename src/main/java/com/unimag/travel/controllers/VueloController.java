package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveVuelo;
import com.unimag.travel.dto.response.GetVuelo;
import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.services.VueloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<GetVuelo>> getAllVuelos() {
        List<GetVuelo> vuelos = vueloService.getAllVuelos();

        return ResponseEntity.ok(vuelos);
    }

    @GetMapping("/{idVuelo}")
    public ResponseEntity<GetVuelo> getVueloById(@PathVariable Long idVuelo) {
        return ResponseEntity.ok(vueloService.getVueloById(idVuelo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idVuelo}")
    public ResponseEntity<GetVuelo> updateVuelo(@PathVariable Long idVuelo, @RequestBody @Valid SaveVuelo saveVuelo) {
        return ResponseEntity.ok(vueloService.updateVueloById(idVuelo, saveVuelo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idVuelo}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long idVuelo) {
        vueloService.deleteVueloById(idVuelo);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    private ResponseEntity<GetVuelo> createVuelo(@RequestBody @Valid SaveVuelo saveVuelo) {
        GetVuelo newVuelo = vueloService.saveVuelo(saveVuelo);


        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(newVuelo);
    }
}

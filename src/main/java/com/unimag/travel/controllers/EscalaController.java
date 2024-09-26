package com.unimag.travel.controllers;

import com.unimag.travel.entities.Escala;
import com.unimag.travel.services.EscalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
    private EscalaService escalaService;

    @Autowired
    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    @GetMapping
    public ResponseEntity<List<Escala>> getAllEscalas(){
        return ResponseEntity.ok(escalaService.getAllEscalas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escala> getEscalaById(@PathVariable Long id){
        return escalaService.getEscalaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Escala "+id+" not found"));
    }

    @PostMapping
    public ResponseEntity<Escala> createOneEscala(@RequestBody Escala escala){
        Escala createdEscala = escalaService.saveEscala(escala);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEscala.getIdEscala())
                .toUri();

        return ResponseEntity.created(newLocation).body(createdEscala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escala> updateEscalaById(@RequestBody Escala escala, @PathVariable Long id){
        Optional<Escala> oldEscala = escalaService.getEscalaById(id);

        return oldEscala.map( e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscala(@PathVariable Long id) {
        escalaService.deleteEscalaById(id);
        return ResponseEntity.noContent().build();
    }
}

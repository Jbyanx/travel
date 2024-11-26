package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveEscala;
import com.unimag.travel.dto.response.GetEscala;
import com.unimag.travel.entities.Escala;
import com.unimag.travel.mapper.EscalaMapper;
import com.unimag.travel.services.EscalaService;
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
@RequestMapping("/escalas")
public class EscalaController {
    private EscalaService escalaService;

    @Autowired
    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    @GetMapping
    public ResponseEntity<List<GetEscala>> getAllEscalas(){
        List<GetEscala> escalaList = escalaService.getAllEscalas();
        return ResponseEntity.ok(escalaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEscala> getEscalaById(@PathVariable Long id){
        return ResponseEntity.ok(escalaService.getEscalaById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GetEscala> createOneEscala(@RequestBody @Valid SaveEscala saveEscala){
        GetEscala createdEscala = escalaService.saveEscala(saveEscala);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEscala.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(createdEscala);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GetEscala> updateEscalaById(@RequestBody @Valid SaveEscala saveEscala, @PathVariable Long id){
        return  ResponseEntity.ok(escalaService.updateEscalaById(id, saveEscala));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscala(@PathVariable Long id) {
        escalaService.deleteEscalaById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.unimag.travel.controllers;

import com.unimag.travel.entities.Reserva;
import com.unimag.travel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas(){
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id){
        return reservaService.getReservaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Reserva "+id+" not found"));
    }

    @PostMapping
    public ResponseEntity<Reserva> createOneReserva(@RequestBody Reserva reserva){
        Reserva createdReserva = reservaService.saveReserva(reserva);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReserva.getIdReserva())
                .toUri();

        return ResponseEntity.created(newLocation).body(createdReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReservaById(@RequestBody Reserva reserva, @PathVariable Long id){
        Optional<Reserva> oldReserva = reservaService.getReservaById(id);

        return oldReserva.map( r -> ResponseEntity.ok(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReservaById(id);
        return ResponseEntity.noContent().build();
    }
}

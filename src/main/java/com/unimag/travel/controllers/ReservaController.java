package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;
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
    public ResponseEntity<List<GetReserva>> getAllReservas(){
        List<GetReserva> reservaList = reservaService.getAllReservas();
        return ResponseEntity.ok(reservaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReserva> getReservaById(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.getReservaById(id));
    }

    @PostMapping
    public ResponseEntity<GetReserva> createOneReserva(@RequestBody SaveReserva saveReserva){
        GetReserva createdReserva = reservaService.createReserva(saveReserva);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReserva.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(createdReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetReserva> updateReservaById(@RequestBody SaveReserva saveReserva, @PathVariable Long id){
        return ResponseEntity.ok(reservaService.updateReservaById(id, saveReserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReservaById(id);
        return ResponseEntity.noContent().build();
    }
}

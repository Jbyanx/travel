package com.unimag.travel.services;

import com.unimag.travel.entities.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    Optional<Reserva> getReservaById(Long id);


    List<Reserva> getAllReservas();

    Reserva saveReserva(Reserva reserva);

    Reserva updateReservaById(Long id, Reserva saveReserva);

    void deleteReservaById(Long id);
}

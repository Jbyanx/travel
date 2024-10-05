package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;
import com.unimag.travel.entities.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    Optional<GetReserva> getReservaById(Long id);


    List<GetReserva> getAllReservas();

    GetReserva saveReserva(SaveReserva saveReserva);

    GetReserva updateReservaById(Long id, SaveReserva saveReserva);

    void deleteReservaById(Long id);
}

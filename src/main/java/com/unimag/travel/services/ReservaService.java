package com.unimag.travel.services;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;

import java.util.List;

public interface ReservaService {

    GetReserva getReservaById(Long id);

    List<GetReserva> getAllReservas();

    GetReserva createReserva(SaveReserva saveReserva);

    GetReserva updateReservaById(Long id, SaveReserva saveReserva);

    void deleteReservaById(Long id);

    List<GetReserva> getReservasByCliente(Long IdCliente);
}

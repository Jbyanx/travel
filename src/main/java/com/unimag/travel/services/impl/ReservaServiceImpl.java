package com.unimag.travel.services.impl;

import com.unimag.travel.entities.Reserva;
import com.unimag.travel.repositories.ReservaRepository;
import com.unimag.travel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }


    @Override
    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Optional<Reserva> getReservaByName(String name) {
        return reservaRepository.getReservaByName(name);
    }

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReservaById(Long id, Reserva saveReserva) {
        Reserva reservaFromDb = reservaRepository.findById(id).get();
        if (reservaFromDb != null) {
            reservaFromDb.setFechaDeReserva(saveReserva.getFechaDeReserva());
            reservaFromDb.setCliente(saveReserva.getCliente());
            reservaFromDb.setNumeroDePasajeros(saveReserva.getNumeroDePasajeros());
        } else{
            throw new RuntimeException("reserva not found");
        }
        return reservaRepository.save(reservaFromDb);
    }

    @Override
    public void deleteReservaById(Long id) {
        reservaRepository.deleteById(id);
    }
}

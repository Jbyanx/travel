package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;
import com.unimag.travel.entities.Reserva;
import com.unimag.travel.mapper.ReservaMapper;
import com.unimag.travel.repositories.ReservaRepository;
import com.unimag.travel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
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
    public Optional<GetReserva> getReservaById(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        return reservaOptional.map(ReservaMapper.INSTANCE::reservaToGetReserva);
    }

    @Override
    public List<GetReserva> getAllReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.INSTANCE.reservaListToGetReservaList(reservas);
    }

    @Override
    public GetReserva createReserva(SaveReserva saveReserva) {
        Reserva reserva = ReservaMapper.INSTANCE.saveReservaToReserva(saveReserva);
        Reserva reservaSaved = reservaRepository.save(reserva);

        return ReservaMapper.INSTANCE.reservaToGetReserva(reservaSaved);
    }

    @Override
    public GetReserva updateReservaById(Long id, SaveReserva saveReserva) {
        Reserva reservaFromDb = reservaRepository.findById(id).get();
        if (reservaFromDb != null) {
            reservaFromDb.setFechaDeReserva(saveReserva.fechaDeReserva());
            reservaFromDb.getCliente().setIdCliente(saveReserva.idCliente());
            reservaFromDb.getVuelo().setIdVuelo(saveReserva.idVuelo());
            reservaFromDb.setNumeroDePasajeros(saveReserva.numeroDePasajeros());
        } else{
            throw new RuntimeException("reserva not found");
        }
        return ReservaMapper.INSTANCE.reservaToGetReserva(reservaRepository.save(reservaFromDb));
    }

    @Override
    public void deleteReservaById(Long id) {
        reservaRepository.deleteById(id);
    }
}

package com.unimag.travel.services.impl;

import com.unimag.travel.dto.request.SaveReserva;
import com.unimag.travel.dto.response.GetReserva;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.entities.Reserva;
import com.unimag.travel.entities.Vuelo;
import com.unimag.travel.exception.ClienteNotFoundException;
import com.unimag.travel.exception.ReservaNotFoundException;
import com.unimag.travel.exception.VueloNotFoundException;
import com.unimag.travel.mapper.ReservaMapper;
import com.unimag.travel.repositories.ClienteRepository;
import com.unimag.travel.repositories.ReservaRepository;
import com.unimag.travel.repositories.VueloRepository;
import com.unimag.travel.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private VueloRepository vueloRepository;

    @Autowired
    public ReservaServiceImpl(ClienteRepository clienteRepository, VueloRepository vueloRepository ,ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.vueloRepository = vueloRepository;
    }


    @Override
    public GetReserva getReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow( () -> new ReservaNotFoundException("reserva id: "+id+" not found"));
        return ReservaMapper.INSTANCE.reservaToGetReserva(reserva);
    }

    @Override
    public List<GetReserva> getAllReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.INSTANCE.reservaListToGetReservaList(reservas);
    }

    @Override
    public GetReserva createReserva(SaveReserva saveReserva) {
        Reserva reserva = ReservaMapper.INSTANCE.saveReservaToReserva(saveReserva);

        Vuelo vuelo = vueloRepository.findById(saveReserva.idVuelo())
                .orElseThrow(() -> new VueloNotFoundException("vuelo no encontrado al guardar reserva"));

        Cliente cliente = clienteRepository.findById(saveReserva.idCliente())
                .orElseThrow(() -> new ClienteNotFoundException("cliente no encontrado al guardar la reserva"));

        reserva.setCliente(cliente);
        reserva.setVuelo(vuelo);

        Reserva reservaSaved = reservaRepository.save(reserva);

        return ReservaMapper.INSTANCE.reservaToGetReserva(reservaSaved);
    }

    @Override
    public GetReserva updateReservaById(Long id, SaveReserva saveReserva) {
        Reserva reservaFromDb = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("reserva id: "+id+" not found"));


        Vuelo vuelo = vueloRepository.findById(saveReserva.idVuelo())
                .orElseThrow(() -> new VueloNotFoundException("vuelo no encontrado al actualizar reserva"));

        Cliente cliente = clienteRepository.findById(saveReserva.idCliente())
                .orElseThrow(() -> new ClienteNotFoundException("cliente no encontrado al actualizar la reserva"));

        reservaFromDb.setFechaDeViaje(saveReserva.fechaDeViaje());
        reservaFromDb.getCliente().setIdCliente(saveReserva.idCliente());
        reservaFromDb.getVuelo().setIdVuelo(saveReserva.idVuelo());
        reservaFromDb.setNumeroDePasajeros(saveReserva.numeroDePasajeros());
        reservaFromDb.setCliente(cliente);
        reservaFromDb.setVuelo(vuelo);

        return ReservaMapper.INSTANCE.reservaToGetReserva(reservaRepository.save(reservaFromDb));
    }

    @Override
    public void deleteReservaById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<GetReserva> getReservasByCliente(Long idCliente) {
        List<Reserva> reservas = reservaRepository.findByCliente_IdCliente(idCliente);
        return ReservaMapper.INSTANCE.reservaListToGetReservaList(reservas);
    }
}

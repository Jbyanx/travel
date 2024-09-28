package com.unimag.travel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    @Column(name = "fecha_de_reserva")
    private LocalDate fechaDeReserva;
    @Column(name = "numero_de_pasajeros")
    private int numeroDePasajeros;
}

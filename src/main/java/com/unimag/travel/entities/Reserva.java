package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @NotNull
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_vuelo")
    @NotNull
    private Vuelo vuelo;

    @Column(name = "fecha_de_reserva")
    @FutureOrPresent(message = "solo puede reservar en fechas presentes o futuras")
    private LocalDate fechaDeReserva;

    @Column(name = "numero_de_pasajeros")
    @Min(value = 1, message = "el numero minimo de pasajeros es 1")
    private int numeroDePasajeros;
}

package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
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
    @NotNull(message = "El cliente es obligatorio para la reserva")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_vuelo")
    @NotNull(message = "El vuelo es obligatorio para la reserva")
    private Vuelo vuelo;

    @Column(name = "fecha_de_reserva")
    @CreationTimestamp
    private LocalDate fechaDeReserva = LocalDate.now();

    @Column(name = "fecha_de_viaje", nullable = false)
    @FutureOrPresent(message = "La fecha de viaje debe ser presente o futura")
    private LocalDate fechaDeViaje = LocalDate.now();

    @Column(name = "numero_de_pasajeros")
    @Min(value = 1, message = "el numero minimo de pasajeros es 1")
    @Max(value = 10, message = "El número máximo de pasajeros es 10")
    private int numeroDePasajeros;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false)
    private EstadoReserva estadoReserva = EstadoReserva.PENDING;
}

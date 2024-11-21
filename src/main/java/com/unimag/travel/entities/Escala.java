package com.unimag.travel.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

import java.time.Duration;

@Entity
@Table(name = "escalas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscala;

    @OneToOne
    @JoinColumn(name = "id_vuelo_escala")
    @NotNull(message = "El vuelo es obligatorio")
    private Vuelo vuelo;

    @OneToOne
    @JoinColumn(name = "id_aeropuerto_escala")
    @NotNull(message = "El aeropuerto es obligatorio")
    private Aeropuerto aeropuerto;

    @Column(name = "tiempo_de_escala")
    @NotNull(message = "El tiempo de escala es obligatorio")
    private Duration tiempoDeEscala;
}

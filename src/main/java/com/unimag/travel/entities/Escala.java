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
    private Long idEscala;

    @OneToOne
    @JoinColumn(name = "id_escala")
    @NotNull
    private Vuelo vuelo;

    @OneToOne
    @JoinColumn(name = "id_escala")
    @NotNull
    private Aeropuerto aeropuerto;

    @Column(name = "tiempo_de_escala")
    @NotNull
    private Duration tiempoDeEscala;
}

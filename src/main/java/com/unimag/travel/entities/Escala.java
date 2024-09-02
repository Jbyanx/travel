package com.unimag.travel.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    //private Vuelo vuelo;

    //private Aeropuerto aeropuerto;

    @Column(name = "tiempo_de_escala")
    private Duration tiempoDeEscala;
}

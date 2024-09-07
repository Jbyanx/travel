package com.unimag.travel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "vuelos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVuelo;
    private String origen;
    private String destino;
    @Column(name = "fecha_de_salida")
    private LocalDate fechaDeSalida;
    @Column(name = "hora_de_salida")
    private LocalTime horaDeSalida;
    private Duration duracion;
    private int capacidad;

    @OneToOne
    @JoinColumn(name = "id_aerolinea")
    private Aerolinea aerolinea;

    @OneToOne
    @JoinColumn(name = "id_aeropuerto_origen")
    private Aeropuerto aeropuertoOrigen;

    @OneToOne
    @JoinColumn(name = "id_aeropuerto_destino")
    private Aeropuerto aeropuertoDestino;
    
    @ManyToMany
    @JoinTable(
            name = "vuelos_escalas",
            joinColumns = @JoinColumn(name = "id_vuelo"),
            inverseJoinColumns = @JoinColumn(name = "id_escala")
    )
    private List<Escala> escalas;
}

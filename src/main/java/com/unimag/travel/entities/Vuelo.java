package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "la ciudad origen del vuelo no debe ir vacia")
    @Size(max = 100)
    private String origen;
    @NotBlank(message = "la ciudad destino del vuelo no debe ir vacia")
    @Size(max = 100)
    private String destino;
    @Column(name = "fecha_de_salida")
    @FutureOrPresent
    private LocalDate fechaDeSalida;
    @Column(name = "hora_de_salida")
    @NotNull
    private LocalTime horaDeSalida;
    @NotNull
    private Duration duracion;
    @Min(value = 1, message = "el vuelo no puede ir sin pasajeros")
    private int capacidad;

    @OneToOne
    @JoinColumn(name = "id_aerolinea")
    @NotNull
    private Aerolinea aerolinea;

    @OneToOne
    @JoinColumn(name = "id_aeropuerto_origen")
    @NotNull
    private Aeropuerto aeropuertoOrigen;

    @OneToOne
    @JoinColumn(name = "id_aeropuerto_destino")
    @NotNull
    private Aeropuerto aeropuertoDestino;
    
    @ManyToMany
    @JoinTable(
            name = "vuelos_escalas",
            joinColumns = @JoinColumn(name = "id_vuelo"),
            inverseJoinColumns = @JoinColumn(name = "id_escala")
    )
    private List<Escala> escalas;
}

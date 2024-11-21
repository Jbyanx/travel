package com.unimag.travel.entities;

import com.unimag.travel.entities.Aerolinea;
import com.unimag.travel.entities.Aeropuerto;
import com.unimag.travel.entities.Escala;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vuelos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVuelo;

    @NotBlank(message = "la ciudad origen del vuelo no debe ir vacia")
    @Size(max = 100, message = "la ciudad origen del vuelo no debe exceder {max} caracteres")
    private String origen;

    @NotBlank(message = "la ciudad destino del vuelo no debe ir vacia")
    @Size(max = 100, message = "la ciudad destino del vuelo no debe exceder {max} caracteres")
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
    @Max(value = 500, message = "La capacidad máxima es 500 pasajeros")
    private int capacidad;

    @ManyToOne
    @JoinColumn(name = "id_aerolinea")
    @NotNull(message = "La aerolínea es obligatoria")
    private Aerolinea aerolinea;

    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_origen")
    @NotNull(message = "El aeropuerto de origen es obligatorio")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_destino")
    @NotNull(message = "El aeropuerto de destino es obligatorio")
    private Aeropuerto aeropuertoDestino;

    @ManyToMany
    @JoinTable(
            name = "vuelos_escalas",
            joinColumns = @JoinColumn(name = "id_vuelo"),
            inverseJoinColumns = @JoinColumn(name = "id_escala")
    )
    private List<Escala> escalas = new ArrayList<>();
}

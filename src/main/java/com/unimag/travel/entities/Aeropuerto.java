package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aeropuertos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aeropuerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAeropuerto;
    @NotBlank(message = "el nombre del aeropuerto no debe ir vacio")
    @Size(max = 100)
    private String nombre;
    @NotBlank(message = "la ciudad del aeropuerto no debe ir vacia")
    @Size(max = 100)
    private String ciudad;
    @NotBlank(message = "el pais del aeropuerto no debe ir vacio")
    @Size(max = 100)
    private String pais;

    @OneToMany(mappedBy = "aeropuertoOrigen")
    private List<Vuelo> vuelosOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "aeropuertoDestino")
    private List<Vuelo> vuelosDestino = new ArrayList<>();
}

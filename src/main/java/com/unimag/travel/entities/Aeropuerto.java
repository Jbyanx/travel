package com.unimag.travel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String nombre;
    private String ciudad;
    private String pais;

    @OneToMany
    @JoinColumn(name = "id_aeropuerto_origen")
    private List<Vuelo> vuelosOrigen;

    @OneToMany
    @JoinColumn(name = "id_aeropuerto_destino")
    private List<Vuelo> vuelosDestino;
}

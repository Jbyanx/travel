package com.unimag.travel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aerolineas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAerolinea;
    private String nombre;
    @Column(name = "codigo_de_aerolinea")
    private Long codigoDeAerolinea;
    @Column(name = "pais_de_origen")
    private String paisDeOrigen;

    @OneToMany
    @JoinColumn(name = "id_aerolinea")
    private List<Vuelo> vuelos;
}

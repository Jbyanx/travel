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
@Table(name = "aerolineas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAerolinea;
    @NotBlank(message = "el nombre de la aerolinea no debe ir vacio")
    @Size(max = 100)
    private String nombre;
    @Column(name = "codigo_de_aerolinea")
    private Long codigoDeAerolinea;
    @Column(name = "pais_de_origen")
    @NotBlank(message = "el pais de origen de la aerolinea no debe ir vacio")
    @Size(max = 100)
    private String paisDeOrigen;

    @OneToMany
    @JoinColumn(name = "id_aerolinea")
    private List<Vuelo> vuelos;
}

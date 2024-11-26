package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aerolineas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAerolinea;
    @NotBlank(message = "el nombre de la aerolinea no debe ir vacio")
    @Size(max = 100, message = "el nombre no debe exceder {max} caracteres")
    private String nombre;
    @Column(name = "codigo_de_aerolinea")
    private Long codigoDeAerolinea;
    @Column(name = "pais_de_origen")
    @NotBlank(message = "el pais de origen de la aerolinea no debe ir vacio")
    @Size(max = 100, message = "el pais de origen no debe exceder {max} caracteres")
    private String paisDeOrigen;

    @OneToMany(mappedBy = "aerolinea")
    private List<Vuelo> vuelos = new ArrayList<>();
}

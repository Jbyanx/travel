package com.unimag.travel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @NotBlank(message = "el nombre del cliente no debe ir vacio")
    @Size(max = 100)
    private String nombre;
    @NotBlank(message = "el apellido del cliente no debe ir vacio")
    @Size(max = 100)
    private String apellido;
    @NotBlank(message = "la direccion del cliente no debe ir vacia")
    @Size(max = 100)
    private String direccion;
    @NotBlank(message = "el telefono del cliente no debe ir vacio")
    @Size(max = 100)
    private String telefono;
    @Column(name = "correo_electronico", unique = true)
    @NotBlank(message = "el correo electronico del cliente no debe ir vacio")
    @Size(max = 255)
    @Email
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clientes_vuelos",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_vuelo")
    )
    private List<Vuelo> vuelos = new ArrayList<>();

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos {min} caracteres")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "id_cliente"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}

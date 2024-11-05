package com.unimag.travel.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unimag.travel.entities.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username; //nombre de usuario (en este caso, el correo electronico)
    private String correoElectronico;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String correoElectronico, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = correoElectronico;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Cliente cliente) {
        List<GrantedAuthority> authorities = cliente.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(cliente.getIdCliente(),
                cliente.getCorreoElectronico(),
                cliente.getPassword(),
                authorities);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

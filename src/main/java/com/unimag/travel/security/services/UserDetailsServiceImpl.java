package com.unimag.travel.security.services;

import com.unimag.travel.entities.Cliente;
import com.unimag.travel.entities.Role;
import com.unimag.travel.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ClienteRepository clienteRepository;

    @Autowired
    public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Metodo para traer una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAutorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
    }

    //Metodo para traer un usuario por medio de username (correoElectronico)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreoElectronico(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        //Convertir un Set de roles a una lista
        List<Role> roles = new ArrayList<>(cliente.getRoles());
        return UserDetailsImpl.build(cliente);
    }
}

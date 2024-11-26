package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.LoginRequest;
import com.unimag.travel.dto.request.SignupRequest;
import com.unimag.travel.dto.response.JwtResponse;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.entities.ERole;
import com.unimag.travel.entities.Role;
import com.unimag.travel.repositories.ClienteRepository;
import com.unimag.travel.repositories.RoleRepository;
import com.unimag.travel.security.jwt.JwtUtil;
import com.unimag.travel.security.services.UserDetailsImpl;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private ClienteRepository clienteRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, ClienteRepository clienteRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.clienteRepository = clienteRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.correoElectronico(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Obtener el token
        String jwtToken = jwtUtil.generateToken(authentication);

        // Extraer los roles de usuario autenticado
        List<String> roles = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Long idCliente = principal.getId();

        return new ResponseEntity<>(new JwtResponse(jwtToken, "Bearer", idCliente, roles),HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest sRequest){
        if(clienteRepository.existsByCorreoElectronico(sRequest.correoElectronico())) {
            return ResponseEntity.badRequest().body("Error: El correo ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(sRequest.nombre());
        cliente.setApellido(sRequest.apellido());
        cliente.setDireccion(sRequest.direccion());
        cliente.setTelefono(sRequest.telefono());
        cliente.setCorreoElectronico(sRequest.correoElectronico());
        cliente.setPassword(passwordEncoder.encode(sRequest.password()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: El role no existe"));
        roles.add(role);

        cliente.setRoles(roles);
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
    }
}

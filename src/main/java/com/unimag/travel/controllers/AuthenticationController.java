package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.LoginRequest;
import com.unimag.travel.dto.request.SignupRequest;
import com.unimag.travel.dto.response.JwtResponse;
import com.unimag.travel.entities.Cliente;
import com.unimag.travel.entities.ERole;
import com.unimag.travel.entities.Role;
import com.unimag.travel.repositories.ClienteRepository;
import com.unimag.travel.repositories.RoleRepository;
import com.unimag.travel.security.Jwt.JwtUtil;
import com.unimag.travel.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.correoElectronico(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest){
        if(clienteRepository.existsByCorreoElectronico(sRequest.correoElectronico())) {
            return ResponseEntity.badRequest().body("Error: El correo ya existe");
        }
        Cliente cliente = new Cliente();
        cliente.setCorreoElectronico(sRequest.correoElectronico());
        cliente.setPassword(passwordEncoder.encode(sRequest.password()));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: El role no existe"));
        roles.add(role);
        cliente.setRoles(roles);
        Cliente newCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(newCliente);
    }

    @GetMapping("/mostrar")
    public String prueba(){
        return "hola";
    }
}

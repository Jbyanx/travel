package com.unimag.travel.repositories;

import com.unimag.travel.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> getClienteByNombre(String name);
    Optional<Cliente> findByCorreoElectronico(String email);
    Boolean existsByCorreoElectronico(String email);
}

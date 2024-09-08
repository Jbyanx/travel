package com.unimag.travel.repositories;

import com.unimag.travel.entities.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
    Optional<Aeropuerto> getAeropuertoByName(String name);
}

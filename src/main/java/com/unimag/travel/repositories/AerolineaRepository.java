package com.unimag.travel.repositories;

import com.unimag.travel.entities.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    Optional<Aerolinea> getAerolineaByName(String name);
}

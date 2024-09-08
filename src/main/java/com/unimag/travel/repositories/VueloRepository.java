package com.unimag.travel.repositories;

import com.unimag.travel.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    Optional<Vuelo> getVueloByName(String name);
}

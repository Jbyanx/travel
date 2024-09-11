package com.unimag.travel.repositories;

import com.unimag.travel.entities.Escala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscalaRepository extends JpaRepository<Escala, Long> {

}

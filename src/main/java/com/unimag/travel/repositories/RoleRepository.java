package com.unimag.travel.repositories;

import com.unimag.travel.entities.ERole;
import com.unimag.travel.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

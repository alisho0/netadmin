package com.alejandrorea.netadmin.repository;

import com.alejandrorea.netadmin.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Optional<Orden> findTopByEscuelaIdOrderByNumeroOrdenDesc(Long escuelaId);
}

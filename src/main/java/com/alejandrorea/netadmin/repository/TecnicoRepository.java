package com.alejandrorea.netadmin.repository;

import com.alejandrorea.netadmin.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}

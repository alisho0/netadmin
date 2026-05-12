package com.alejandrorea.netadmin.service;

import com.alejandrorea.netadmin.dtos.escuela.CrearEscuelaRequest;
import com.alejandrorea.netadmin.models.Escuela;
import com.alejandrorea.netadmin.repository.EscuelaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EscuelaService {
    private final EscuelaRepository escuelaRepository;

    public void crearEscuela(CrearEscuelaRequest request) {
        if (request.getNombre() == null) {
            throw new RuntimeException("El nombre no debe ser nulo");
        }
        Escuela escuela = new Escuela();
        escuela.setNombre(request.getNombre());
        escuela.setLocalidad(request.getLocalidad() != null ? request.getLocalidad() : "Sin asignar");

        escuelaRepository.save(escuela);
    }
}

package com.alejandrorea.netadmin.controller;

import com.alejandrorea.netadmin.dtos.escuela.CrearEscuelaRequest;
import com.alejandrorea.netadmin.service.EscuelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/escuelas")
public class EscuelaController {
    private final EscuelaService escuelaService;

    @PostMapping
    public ResponseEntity<?> crearEscuela(@RequestBody CrearEscuelaRequest request) {
        escuelaService.crearEscuela(request);
        return ResponseEntity.ok("Escuela creada correctamente.");
    }
}

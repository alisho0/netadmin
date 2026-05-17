package com.alejandrorea.netadmin.controller;

import com.alejandrorea.netadmin.dtos.orden.CrearOrdenRequest;
import com.alejandrorea.netadmin.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ordenes")
public class OrdenController {
    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody CrearOrdenRequest request) {
        ordenService.crearOrden(request);
        return ResponseEntity.ok("Orden creada correctamente");
    }
    @GetMapping
    public ResponseEntity<?> obtenerOrdenes(){
        return ResponseEntity.ok(ordenService.obtenerOrdenes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOrden(@PathVariable Long id){
        return ResponseEntity.ok(ordenService.obtenerOrden(id));
    }

}

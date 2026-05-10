package com.alejandrorea.netadmin.service;

import com.alejandrorea.netadmin.dtos.laptop.LaptopRequest;
import com.alejandrorea.netadmin.dtos.orden.CrearOrdenRequest;
import com.alejandrorea.netadmin.models.Escuela;
import com.alejandrorea.netadmin.models.Estado;
import com.alejandrorea.netadmin.models.Laptop;
import com.alejandrorea.netadmin.models.Orden;
import com.alejandrorea.netadmin.repository.EscuelaRepository;
import com.alejandrorea.netadmin.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrdenService {
    private final OrdenRepository ordenRepository;
    private final EscuelaRepository escuelaRepository;

    public void crearOrden(CrearOrdenRequest request) {

        Escuela esc = escuelaRepository.findById(request.getEscuelaId())
                .orElseThrow(() -> new RuntimeException("No se encontró el id de la escuela"));
        Integer nuevoNumeroOrden = generarNumeroOrden(esc.getId());

        Orden orden = new Orden();

        orden.setEscuela(esc);
        orden.setNumeroOrden(nuevoNumeroOrden);
        orden.setFechaIngreso(LocalDate.now());
        orden.setFechaLimite(request.getFechaLimite());

        List<Laptop> laptops = new ArrayList<>();

        int posicion = 1;

        for (LaptopRequest laptopRequest : request.getLaptops()) {

            Laptop laptop = new Laptop();
            if (laptopRequest.getCodigoBarra() != null) {
                laptop.setCodigoBarra(laptopRequest.getCodigoBarra());
            }
            laptop.setDescripcionProblema(laptopRequest.getDescripcionProblema());
            laptop.setEstado(Estado.INGRESADA);
            laptop.setPosicionEnOrden(posicion++);
            laptop.setOrden(orden);
            laptops.add(laptop);
        }

        orden.setLaptops(laptops);

        ordenRepository.save(orden);
    }

    @Transactional
    public List<Orden> obtenerOrdenes() {
        return ordenRepository.findAll();
    }

    @Transactional
    public Orden obtenerOrden(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    private Integer generarNumeroOrden(Long escuelaId) {

        Optional<Orden> ultimaOrden =
                ordenRepository.findTopByEscuelaIdOrderByNumeroOrdenDesc(escuelaId);

        return ultimaOrden.map(orden -> orden.getNumeroOrden() + 1)
                .orElse(1);
    }
}

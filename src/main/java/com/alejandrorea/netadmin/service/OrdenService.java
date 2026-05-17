package com.alejandrorea.netadmin.service;

import com.alejandrorea.netadmin.dtos.laptop.DetalleLaptopDTO;
import com.alejandrorea.netadmin.dtos.laptop.LaptopRequest;
import com.alejandrorea.netadmin.dtos.orden.CrearOrdenRequest;
import com.alejandrorea.netadmin.dtos.orden.DetalleOrdenDTO;
import com.alejandrorea.netadmin.dtos.orden.OrdenRequestDTO;
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
import java.util.stream.Collectors;

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
    public List<OrdenRequestDTO> obtenerOrdenes() {
        List<Orden> ordenes = ordenRepository.findAll();
        List<OrdenRequestDTO> ordenesDTO = ordenes.stream()
                .map(o -> OrdenRequestDTO.builder()
                        .id(o.getId())
                        .numeroOrden(o.getNumeroOrden())
                        .fechaIngreso(o.getFechaIngreso())
                        .fechaLimite(o.getFechaLimite())
                        .escuela(o.getEscuela())
                        .cantLaptops(o.getLaptops().size())
                        .build())
                .collect(Collectors.toList());
        return ordenesDTO;
    }

    @Transactional
    public DetalleOrdenDTO obtenerOrden(Long id) {
        Orden o = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        
        return DetalleOrdenDTO.builder()
                .id(o.getId())
                .numeroOrden(o.getNumeroOrden())
                .fechaIngreso(o.getFechaIngreso())
                .fechaLimite(o.getFechaLimite())
                .escuela(o.getEscuela().getNombre())
                .laptops(o.getLaptops().stream()
                        .map(l -> DetalleLaptopDTO.builder()
                                .id(l.getId())
                                .descripcionProblema(l.getDescripcionProblema())
                                .codigoBarra(l.getCodigoBarra())
                                .posicionEnOrden(l.getPosicionEnOrden())
                                .estado(l.getEstado())
                                .build())
                        .collect(Collectors.toUnmodifiableList()))
                .build();
    }

    private Integer generarNumeroOrden(Long escuelaId) {

        Optional<Orden> ultimaOrden =
                ordenRepository.findTopByEscuelaIdOrderByNumeroOrdenDesc(escuelaId);

        return ultimaOrden.map(orden -> orden.getNumeroOrden() + 1)
                .orElse(1);
    }
}

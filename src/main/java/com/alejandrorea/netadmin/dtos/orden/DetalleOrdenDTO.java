package com.alejandrorea.netadmin.dtos.orden;

import com.alejandrorea.netadmin.dtos.laptop.DetalleLaptopDTO;
import com.alejandrorea.netadmin.models.Laptop;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DetalleOrdenDTO {
    private Long id;
    private Integer numeroOrden;

    private LocalDate fechaIngreso;
    private LocalDate fechaLimite;
    private String escuela;
    private List<DetalleLaptopDTO> laptops;
}

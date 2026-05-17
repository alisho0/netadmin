package com.alejandrorea.netadmin.dtos.orden;

import com.alejandrorea.netadmin.models.Escuela;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrdenRequestDTO {
    private Long id;
    private Escuela escuela;
    private LocalDate fechaIngreso;
    private LocalDate fechaLimite;
    private int cantLaptops;
    private Integer numeroOrden;
}

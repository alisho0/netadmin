package com.alejandrorea.netadmin.dtos.orden;

import com.alejandrorea.netadmin.dtos.laptop.LaptopRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CrearOrdenRequest {
    private Long escuelaId;
    private LocalDate fechaLimite;
    private List<LaptopRequest> laptops;
}

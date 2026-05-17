package com.alejandrorea.netadmin.dtos.laptop;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LaptopRequest {
    private String descripcionProblema;
    private String codigoBarra;
}

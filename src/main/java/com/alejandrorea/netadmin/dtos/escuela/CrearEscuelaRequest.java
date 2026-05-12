package com.alejandrorea.netadmin.dtos.escuela;

import lombok.Data;

@Data
public class CrearEscuelaRequest {
    private String nombre;
    private String localidad;
}

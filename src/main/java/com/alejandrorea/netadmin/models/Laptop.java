package com.alejandrorea.netadmin.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoBarra;

    @ManyToOne
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private Orden orden;
    private Integer posicionEnOrden;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String descripcionProblema;
    /*
codigo_barra (UNIQUE)
escuela_id
fecha_ingreso
fecha_limite
estado (ENUM: INGRESADA, EN_REPARACION, REPARADA, DEVUELTA)
descripcion_problema
    */
}

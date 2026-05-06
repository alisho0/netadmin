package com.alejandrorea.netadmin.models;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo_barra;

    @OneToOne
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private Orden orden;

    private Estado estado;
    private String descripcion_problema;
    /*
codigo_barra (UNIQUE)
escuela_id
fecha_ingreso
fecha_limite
estado (ENUM: INGRESADA, EN_REPARACION, REPARADA, DEVUELTA)
descripcion_problema
    */
}

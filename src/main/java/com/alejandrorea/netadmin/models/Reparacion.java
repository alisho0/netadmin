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
@Entity(name = "reparacion")
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id", referencedColumnName = "id")
    private Laptop laptop;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
    private Tecnico tecnico;
    
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String descripcion;
    /*
        id
        netbook_id
        tecnico_id
        fecha_inicio
        fecha_fin
        descripcion
        estado
    */
}

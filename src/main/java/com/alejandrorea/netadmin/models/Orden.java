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
@Entity(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero_orden;

    private LocalDate fecha_ingreso;
    private LocalDate fecha_limite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "escuela_id", referencedColumnName = "id")
    private Escuela escuela;
    
}

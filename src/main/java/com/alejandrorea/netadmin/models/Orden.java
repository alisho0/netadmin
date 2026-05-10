package com.alejandrorea.netadmin.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroOrden;

    private LocalDate fechaIngreso;
    private LocalDate fechaLimite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "escuela_id", referencedColumnName = "id")
    private Escuela escuela;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "orden_id")
    private List<Laptop> laptops;
}

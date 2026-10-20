package com.salesianos.tracky.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Departamento {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private double presupuesto;

    @OneToMany (mappedBy = "departamento", fetch = FetchType.EAGER)
    @Builder.Default
    private List <Empleado> listaEmpleados = new ArrayList<>();





}

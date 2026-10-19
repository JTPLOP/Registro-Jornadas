package com.salesianos.tracky.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


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


}

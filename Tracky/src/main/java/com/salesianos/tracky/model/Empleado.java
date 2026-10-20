package com.salesianos.tracky.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Empleado {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String cargo;

    private double salario;


    // ASOCIACION N:1 CON DEPARTAMENTOS

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_departament",
        foreignKey = @ForeignKey (name = "fk_employer_department"))
    private Departamento departamento;



    public void addToDepartamento (Departamento departamento) {

        this.departamento = departamento;
        departamento.getListaEmpleados().add(this);
    }

    public void removeToDepartamento (Departamento departamento) {

        departamento.getListaEmpleados().remove(this);
        this.departamento = null;
    }

    // ASOCIACION 1:N CON FICHAJES

    @OneToMany (mappedBy = "empleado", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Fichaje> historialFichajes = new ArrayList<>();

}

package com.salesianos.tracky.model.DTOs.departamento;

import com.salesianos.tracky.model.Departamento;

public record UpdateDepartamentoRequest(

    String nombre,
    double presupuesto

) {

    public Departamento to() {
        return Departamento.builder()
                .nombre(nombre)
                .presupuesto(presupuesto)
                .build();
    }
}

package com.salesianos.tracky.model.DTOs.empleado;

import com.salesianos.tracky.model.Empleado;

public record UpdateEmpleadoRequest(
        String nombre,
        String cargo,
        double salario,
        Long idDepartamento
) {

    public Empleado to() {
        return Empleado.builder()
                .nombre(nombre)
                .cargo(cargo)
                .salario(salario)
                .build();
    }
}

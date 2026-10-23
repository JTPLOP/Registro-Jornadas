package com.salesianos.tracky.model.DTOs.empleado;

import com.salesianos.tracky.model.Empleado;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record UpdateEmpleadoRequest(
        @NotEmpty(message = "Debe haber nombre") String nombre,
        @NotEmpty(message = "Debe haber cargo") String cargo,
        @Min(value = 0, message = "Min 0") double salario,

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

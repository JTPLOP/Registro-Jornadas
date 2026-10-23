package com.salesianos.tracky.model.DTOs.empleado;

import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.validation.PresupuestoSuficiente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



public record UpdateEmpleadoRequest(
        @NotBlank(message = "{empleado.nombre.notblank}")
        String nombre,


        @NotBlank(message = "{empleado.cargo.notblank}")
        String cargo,


        @Min(value = 0, message = "{empleado.salario.min}")
        double salario,

        @PresupuestoSuficiente(message = "{empleado.idDepartamento.presupuesto}")
        @NotNull(message = "{empleado.idDepartamento.notnull}")
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

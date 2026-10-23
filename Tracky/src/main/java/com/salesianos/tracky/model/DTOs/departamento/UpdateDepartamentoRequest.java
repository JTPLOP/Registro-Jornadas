package com.salesianos.tracky.model.DTOs.departamento;

import com.salesianos.tracky.model.Departamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UpdateDepartamentoRequest(


    @NotBlank(message = "{departamento.nombre.notblank}") String nombre,
    @Min(value = 0, message = "{departamento.presupuesto.min}") double presupuesto

) {

    public Departamento to() {
        return Departamento.builder()
                .nombre(nombre)
                .presupuesto(presupuesto)
                .build();
    }
}

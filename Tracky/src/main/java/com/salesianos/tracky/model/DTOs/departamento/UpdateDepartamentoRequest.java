package com.salesianos.tracky.model.DTOs.departamento;

import com.salesianos.tracky.model.Departamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record UpdateDepartamentoRequest(


    @NotEmpty(message = "{departamentoDto.nombre.notempty}") String nombre,
    @Min(value = 0, message = "Valor por encima 0") double presupuesto

) {

    public Departamento to() {
        return Departamento.builder()
                .nombre(nombre)
                .presupuesto(presupuesto)
                .build();
    }
}

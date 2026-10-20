package com.salesianos.tracky.model.DTOs.departamento;

import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.model.Empleado;

import java.util.List;

public record GetDepartamentoResponse(

    String nombre,
    double presupuesto
) {

    public static GetDepartamentoResponse of(Departamento departamento) {

        return new GetDepartamentoResponse(
                departamento.getNombre(),
                departamento.getPresupuesto()
        );
    }
}

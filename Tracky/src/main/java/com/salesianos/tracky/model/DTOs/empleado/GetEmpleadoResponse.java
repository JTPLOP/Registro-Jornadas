package com.salesianos.tracky.model.DTOs.empleado;

import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.model.Fichaje;

import java.util.List;

public record GetEmpleadoResponse(
        String nombre,
        String cargo,
        double salario,
        String nombreDepartamento
) {

    public static GetEmpleadoResponse of(Empleado empleado) {
        return new GetEmpleadoResponse(
                empleado.getNombre(),
                empleado.getCargo(),
                empleado.getSalario(),
                empleado.getDepartamento().getNombre() != null ? empleado.getDepartamento().getNombre() : "Sin departamento"
        );
    }
}

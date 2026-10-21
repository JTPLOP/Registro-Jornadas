package com.salesianos.tracky.model.DTOs.empleado;

import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.model.Fichaje;

import java.util.List;

public record GetEmpleadoResponse(
        String nombre,
        String cargo,
        double salario,
        String nombreDepartamento,
        List <Fichaje> listFichaje
) {

    public static GetEmpleadoResponse of(Empleado empleado) {
        return new GetEmpleadoResponse(
                empleado.getNombre(),
                empleado.getCargo(),
                empleado.getSalario(),
                empleado.getDepartamento().getNombre(),
                empleado.getHistorialFichajes()
        );
    }
}

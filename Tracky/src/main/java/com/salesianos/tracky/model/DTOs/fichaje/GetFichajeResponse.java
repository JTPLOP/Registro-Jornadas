package com.salesianos.tracky.model.DTOs.fichaje;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Fichaje;

import java.time.LocalDateTime;

public record GetFichajeResponse(
        LocalDateTime fechaAccion,
        TipoFichaje tipoFichaje,
        String nombreEmpleado

) {

    public static GetFichajeResponse of (Fichaje fichaje) {
        return new GetFichajeResponse(
                fichaje.getFechaAccion(),
                fichaje.getTipoFichaje(),
                fichaje.getEmpleado().getNombre()
        );
    }

}

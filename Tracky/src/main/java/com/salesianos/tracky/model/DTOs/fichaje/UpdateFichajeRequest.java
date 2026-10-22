package com.salesianos.tracky.model.DTOs.fichaje;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.model.Fichaje;

import java.time.LocalDateTime;

public record UpdateFichajeRequest(
        TipoFichaje tipoFichaje,
        Empleado empleado

) {
    public Fichaje to() {
        return Fichaje.builder()
                .tipoFichaje(this.tipoFichaje)
                .build();
    }

}

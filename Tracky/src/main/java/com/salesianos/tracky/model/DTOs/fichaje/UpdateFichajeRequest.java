package com.salesianos.tracky.model.DTOs.fichaje;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Fichaje;

import java.time.LocalDateTime;

public record UpdateFichajeRequest(
        TipoFichaje tipoFichaje,
        Long idEmpleado

) {
    public Fichaje to() {
        return Fichaje.builder()
                .tipoFichaje(this.tipoFichaje)
                .build();
    }

}

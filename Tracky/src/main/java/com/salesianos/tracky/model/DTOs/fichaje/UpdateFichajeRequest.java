package com.salesianos.tracky.model.DTOs.fichaje;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Fichaje;

import java.time.LocalDateTime;

public record UpdateFichajeRequest(
        Enum<TipoFichaje> tipoFichaje,
        Long idEmpleado

) {
    public Fichaje to() {
        return Fichaje.builder()
                .tipoFichaje((TipoFichaje) tipoFichaje)
                .build();
    }

}

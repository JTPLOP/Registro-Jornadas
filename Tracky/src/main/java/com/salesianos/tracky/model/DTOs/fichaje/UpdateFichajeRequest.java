package com.salesianos.tracky.model.DTOs.fichaje;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Fichaje;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateFichajeRequest(

        @NotNull(message = "{fichaje.tipoFichaje.notnull}")
        TipoFichaje tipoFichaje,

        @NotNull(message = "{fichaje.idEmpleado.notnull}")
        Long idEmpleado

) {
    public Fichaje to() {
        return Fichaje.builder()
                .tipoFichaje(this.tipoFichaje)
                .build();
    }

}

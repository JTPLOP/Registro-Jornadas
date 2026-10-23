package com.salesianos.tracky.service;


import com.salesianos.tracky.model.DTOs.fichaje.GetFichajeResponse;
import com.salesianos.tracky.model.DTOs.fichaje.UpdateFichajeRequest;
import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.model.Fichaje;
import com.salesianos.tracky.repository.EmpleadoRepository;
import com.salesianos.tracky.repository.FichajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FichajeService {

    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;

    public GetFichajeResponse addFichaje (Empleado empleado) {

        UpdateFichajeRequest fichajeDTO = new UpdateFichajeRequest(ultimoTipoFichaje(empleado), empleado.getId());

        Fichaje fichaje = fichajeDTO.to();

        fichaje.setEmpleado(empleado);
        fichaje.setFechaAccion(LocalDateTime.now());

        empleado.getHistorialFichajes().add(fichaje);

        return GetFichajeResponse.of(fichaje);

    }

    public List<Fichaje> getAllFichajesEmpleado (Empleado empleado) {

        List<Fichaje> historial = empleado.getHistorialFichajes();

        return historial;

    }

    public TipoFichaje ultimoTipoFichaje (Empleado empleado) {

        List<Fichaje> historial = empleado.getHistorialFichajes();

        if (historial.isEmpty())
            return TipoFichaje.ENTRADA;

        Fichaje lastFichaje = historial.get(historial.size() - 1);

        if (lastFichaje.getTipoFichaje().name().equalsIgnoreCase(TipoFichaje.ENTRADA.name()))
            return TipoFichaje.ENTRADA;
        else {
            return TipoFichaje.SALIDA;
        }

    }

}

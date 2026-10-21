package com.salesianos.tracky.service;


import com.salesianos.tracky.model.DTOs.fichaje.UpdateFichajeRequest;
import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.model.Fichaje;
import com.salesianos.tracky.repository.EmpleadoRepository;
import com.salesianos.tracky.repository.FichajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FichajeService {

    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;

    public Empleado addFichaje (UpdateFichajeRequest fichajeDTO) {

        Empleado empleado = empleadoRepository.findById(fichajeDTO.idEmpleado())
                .orElseThrow(() -> new RuntimeException());

        Fichaje fichaje = fichajeDTO.to();

        empleado.getHistorialFichajes().add(fichaje);

        return empleadoRepository.save(empleado);

    }

}

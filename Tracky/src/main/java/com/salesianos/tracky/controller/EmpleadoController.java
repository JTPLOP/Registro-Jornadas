package com.salesianos.tracky.controller;


import com.salesianos.tracky.model.DTOs.empleado.GetEmpleadoResponse;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.DTOs.fichaje.UpdateFichajeRequest;
import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.service.EmpleadoService;
import com.salesianos.tracky.service.FichajeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final FichajeService fichajeService;

    @GetMapping
    public ResponseEntity<List<GetEmpleadoResponse>> getAllEmployers () {

        List<Empleado> resultBusqueda = empleadoService.findAllEmployers();

        return ResponseEntity.ok(resultBusqueda
                .stream()
                .map(GetEmpleadoResponse::of)
                .toList());

    }

    @GetMapping ("/{id}")
    public ResponseEntity <GetEmpleadoResponse> getEmployer (
            @PathVariable Long id
    ) {

        Empleado resultBusqueda = empleadoService.findEmployerById(id);

        return ResponseEntity.ok(GetEmpleadoResponse.of(resultBusqueda));

    }


    @PostMapping
    public ResponseEntity<Empleado> addEmployer (
            @RequestBody UpdateEmpleadoRequest empleado
    ) {

        return ResponseEntity.status(201)
                .body(empleadoService.addEmployer(empleado));

    }

    @PostMapping ("tracker/{id}")
    public ResponseEntity <Empleado> addTrackEmployer (
            @RequestBody UpdateFichajeRequest fichajeDTO
            ) {

        return ResponseEntity.status(201)
                .body(fichajeService.addFichaje(fichajeDTO));

    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteEmployer(
             @PathVariable Long id
    ) {

        empleadoService.deleteEmployer(id);
        return ResponseEntity.noContent().build();

    }

}

package com.salesianos.tracky.controller;


import com.salesianos.tracky.model.DTOs.empleado.GetEmpleadoResponse;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

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

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteEmployer(
             @PathVariable Long id
    ) {

        empleadoService.deleteEmployer(id);
        return ResponseEntity.noContent().build();

    }

}

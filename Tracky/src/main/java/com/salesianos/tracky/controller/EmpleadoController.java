package com.salesianos.tracky.controller;


import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.service.EmpleadoService;
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

    @GetMapping
    public ResponseEntity <List<Empleado>> getAllEmployers () {

        List<Empleado> resultBusqueda = empleadoService.findAllEmployers();

        return ResponseEntity.ok (resultBusqueda);

    }

    @GetMapping ("/{id}")
    public ResponseEntity <Empleado> getEmployer (
            @PathVariable Long id
    ) {

        Empleado resultBusqueda = empleadoService.findEmployerById(id);

        return ResponseEntity.ok(resultBusqueda);

    }

    @PostMapping
    public ResponseEntity<Empleado> addEmployer (
            @RequestBody Empleado empleado
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

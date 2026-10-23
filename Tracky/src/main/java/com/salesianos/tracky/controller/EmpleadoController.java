package com.salesianos.tracky.controller;


import com.salesianos.tracky.model.DTOs.empleado.GetEmpleadoResponse;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.DTOs.fichaje.GetFichajeResponse;
import com.salesianos.tracky.model.DTOs.fichaje.UpdateFichajeRequest;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.service.DepartamentoService;
import com.salesianos.tracky.service.EmpleadoService;
import com.salesianos.tracky.service.FichajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final FichajeService fichajeService;
    private final DepartamentoService departamentoService;

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

        return ResponseEntity.ok(GetEmpleadoResponse.of(empleadoService.findEmployerById(id)));

    }

    @GetMapping ("/{id}/fichajes")
    public ResponseEntity<List<GetFichajeResponse>> getAllFichajesEmpleado (
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(fichajeService.getAllFichajesEmpleado(
                empleadoService.findEmployerById(id)
        )
                .stream()
                .map(GetFichajeResponse::of)
                .toList());

    }


    @PostMapping
    public ResponseEntity<Empleado> addEmployer (
            @RequestBody UpdateEmpleadoRequest empleado
    ) {

        return ResponseEntity.status(201)
                .body(empleadoService.addEmployer(empleado));

    }

    @PostMapping ("tracker/{id}")
    public ResponseEntity <GetFichajeResponse> addTrackEmployer (
            @RequestBody UpdateFichajeRequest fichajeDTO
            ) {

        return ResponseEntity.status(201)
                .body(fichajeService.addFichaje(fichajeDTO));

    }

    @PutMapping ("/{id}")
    public ResponseEntity <GetEmpleadoResponse> updateEmpleado (
            @PathVariable Long id,
            @RequestBody UpdateEmpleadoRequest empleadoDTO
    ) {

        return ResponseEntity.status(200)
                .body(empleadoService.updateEmpleado(id, empleadoDTO));

    }

    @PutMapping ("/{idEmple}/departamento/{/idDepart}")
    public ResponseEntity <GetEmpleadoResponse> updateEmpleadoDepart (
            @PathVariable Long idEmple,
            @PathVariable Long idDepart
    ) {

        return ResponseEntity.status(200)
                .body(empleadoService.updateDepartEmpleado(empleadoService.findEmployerById(idEmple),
                        departamentoService.findById(idDepart)));
    }



    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteEmployer(
             @PathVariable Long id
    ) {

        empleadoService.deleteEmployer(id);
        return ResponseEntity.noContent().build();

    }

}

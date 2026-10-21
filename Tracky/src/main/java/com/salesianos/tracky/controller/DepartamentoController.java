package com.salesianos.tracky.controller;


import com.salesianos.tracky.model.DTOs.departamento.GetDepartamentoResponse;
import com.salesianos.tracky.model.DTOs.departamento.UpdateDepartamentoRequest;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/departamento")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<GetDepartamentoResponse>> getAllDepartamentos () {

        List<Departamento> resultBusqueda = departamentoService.findAll();

        return ResponseEntity.ok(resultBusqueda
                .stream()
                .map(GetDepartamentoResponse::of)
                .toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDepartamentoResponse> getDepartamentById(
            @PathVariable Long id
    ) {

        Departamento resultBusqueda = departamentoService.findById(id);


        return ResponseEntity.ok(GetDepartamentoResponse.of(resultBusqueda));

    }

    @PostMapping
    public ResponseEntity<Departamento> addDepartment(
            @RequestBody UpdateDepartamentoRequest departamento) {

        return ResponseEntity.status(201)
                .body(departamentoService.addDepartment(departamento));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteDepartment (
            @PathVariable Long id
    ) {

        departamentoService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }


}

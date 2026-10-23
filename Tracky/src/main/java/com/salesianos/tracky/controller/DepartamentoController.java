package com.salesianos.tracky.controller;

import com.salesianos.tracky.model.DTOs.departamento.GetDepartamentoResponse;
import com.salesianos.tracky.model.DTOs.departamento.UpdateDepartamentoRequest;
import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.service.DepartamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departamento")
@RequiredArgsConstructor
@Validated
@Tag(name = "Departamento", description = "Controlador para la gestión de departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @Operation(
            summary = "Obtener todos los departamentos",
            description = "Permite obtener la lista completa de todos los departamentos registrados"
    )
    @ApiResponse(description = "Listado de departamentos",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GetDepartamentoResponse.class)),
                    examples = {
                            @ExampleObject("""
                                    [
                                        {
                                            "nombre": "Desarrollo",
                                            "presupuesto": 50000.0
                                        },
                                        {
                                            "nombre": "Recursos Humanos",
                                            "presupuesto": 25000.0
                                        }
                                    ]
                                """)
                    }
            )
    )
    @GetMapping
    public ResponseEntity<List<GetDepartamentoResponse>> getAllDepartamentos() {

        List<Departamento> resultBusqueda = departamentoService.findAll();

        return ResponseEntity.ok(resultBusqueda
                .stream()
                .map(GetDepartamentoResponse::of)
                .toList());

    }

    @Operation(
            summary = "Obtener un departamento concreto",
            description = "Permite obtener la información de un departamento concreto si se le proporciona un id"
    )
    @ApiResponse(description = "Información detallada de un departamento",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetDepartamentoResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "nombre": "Desarrollo",
                                        "presupuesto": 50000.0
                                    }
                                """)
                    }
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<GetDepartamentoResponse> getDepartamentById(
            @Parameter(description = "Identificador del departamento") @PathVariable Long id
    ) {

        Departamento resultBusqueda = departamentoService.findById(id);

        return ResponseEntity.ok(GetDepartamentoResponse.of(resultBusqueda));

    }

    @Operation(
            summary = "Crear un departamento",
            description = "Permite crear un nuevo departamento"
    )
    @ApiResponse(description = "Departamento recién creado",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Departamento.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "nombre": "Desarrollo",
                                        "presupuesto": 50000.0,
                                        "listaEmpleados": []
                                    }
                                """)
                    }
            )
    )
    @PostMapping
    public ResponseEntity<GetDepartamentoResponse> addDepartment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Departamento a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateDepartamentoRequest.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Desarrollo",
                                        "presupuesto": 50000.0
                                    }
                                """)
                    )
            )
            @Valid @RequestBody UpdateDepartamentoRequest departamento) {

        return ResponseEntity.status(201)
                .body(departamentoService.addDepartment(departamento));
    }

    @Operation(
            summary = "Editar un departamento",
            description = "Permite editar la información de un departamento si se proporciona su ID"
    )
    @ApiResponse(description = "Departamento editado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetDepartamentoResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "nombre": "Innovación y Desarrollo",
                                        "presupuesto": 60000.0
                                    }
                                """)
                    }
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<GetDepartamentoResponse> updateDepartment(
            @Parameter(description = "Identificador del departamento a editar") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos a editar en el departamento", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateDepartamentoRequest.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Innovación y Desarrollo",
                                        "presupuesto": 60000.0
                                    }
                                """)
                    )
            )
            @Valid @RequestBody UpdateDepartamentoRequest departmentDTO
    ) {

        return ResponseEntity.status(200)
                .body(departamentoService.updateDepartment(departmentDTO, id));

    }

    @Operation(
            summary = "Eliminar un departamento",
            description = "Permite eliminar un departamento si se proporciona su ID"
    )
    @ApiResponse(description = "Respuesta correcta de departamento eliminado",
            responseCode = "204",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @Parameter(description = "Identificador del departamento a eliminar") @PathVariable Long id
    ) {

        departamentoService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}

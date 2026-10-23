package com.salesianos.tracky.controller;

import com.salesianos.tracky.model.DTOs.empleado.GetEmpleadoResponse;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.DTOs.fichaje.GetFichajeResponse;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.service.DepartamentoService;
import com.salesianos.tracky.service.EmpleadoService;
import com.salesianos.tracky.service.FichajeService;
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
@RequestMapping("api/empleado")
@RequiredArgsConstructor
@Validated
@Tag(name = "Empleado", description = "Controlador para la gestión de empleados y sus fichajes")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final FichajeService fichajeService;
    private final DepartamentoService departamentoService;

    @Operation(
            summary = "Obtener todos los empleados",
            description = "Permite obtener la lista completa de todos los empleados registrados"
    )
    @ApiResponse(description = "Listado de empleados",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GetEmpleadoResponse.class)),
                    examples = {
                            @ExampleObject("""
                                    [
                                        {
                                            "nombre": "Juan Pérez",
                                            "cargo": "Desarrollador Senior",
                                            "salario": 3500.0,
                                            "nombreDepartamento": "Desarrollo",
                                            "listFichaje": []
                                        },
                                        {
                                            "nombre": "Ana López",
                                            "cargo": "Responsable de RRHH",
                                            "salario": 2800.0,
                                            "nombreDepartamento": "Recursos Humanos",
                                            "listFichaje": []
                                        }
                                    ]
                                """)
                    }
            )
    )
    @GetMapping
    public ResponseEntity<List<GetEmpleadoResponse>> getAllEmployers() {

        List<Empleado> resultBusqueda = empleadoService.findAllEmployers();

        return ResponseEntity.ok(resultBusqueda
                .stream()
                .map(GetEmpleadoResponse::of)
                .toList());

    }

    @Operation(
            summary = "Obtener un empleado concreto",
            description = "Permite obtener la información de un empleado concreto si se le proporciona un id"
    )
    @ApiResponse(description = "Información detallada de un empleado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetEmpleadoResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "nombre": "Juan Pérez",
                                        "cargo": "Desarrollador Senior",
                                        "salario": 3500.0,
                                        "nombreDepartamento": "Desarrollo",
                                        "listFichaje": [
                                            {
                                                "id": 1,
                                                "fechaAccion": "2026-10-23T08:00:00",
                                                "tipoFichaje": "ENTRADA"
                                            }
                                        ]
                                    }
                                """)
                    }
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<GetEmpleadoResponse> getEmployer(
            @Parameter(description = "Identificador del empleado") @PathVariable Long id
    ) {

        return ResponseEntity.ok(GetEmpleadoResponse.of(empleadoService.findEmployerById(id)));

    }

    @Operation(
            summary = "Obtener todos los fichajes de un empleado",
            description = "Permite obtener el listado de todos los fichajes de un empleado concreto si se proporciona su id"
    )
    @ApiResponse(description = "Listado de fichajes del empleado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GetFichajeResponse.class)),
                    examples = {
                            @ExampleObject("""
                                    [
                                        {
                                            "fechaAccion": "2026-10-23T08:00:00",
                                            "tipoFichaje": "ENTRADA",
                                            "nombreEmpleado": "Juan Pérez"
                                        },
                                        {
                                            "fechaAccion": "2026-10-23T14:30:00",
                                            "tipoFichaje": "SALIDA",
                                            "nombreEmpleado": "Juan Pérez"
                                        }
                                    ]
                                """)
                    }
            )
    )
    @GetMapping("/{id}/fichajes")
    public ResponseEntity<List<GetFichajeResponse>> getAllFichajesEmpleado(
            @Parameter(description = "Identificador del empleado") @PathVariable Long id
    ) {

        return ResponseEntity.ok(fichajeService.getAllFichajesEmpleado(
                empleadoService.findEmployerById(id)
        )
                .stream()
                .map(GetFichajeResponse::of)
                .toList());

    }

    @Operation(
            summary = "Crear un empleado",
            description = "Permite crear un nuevo empleado asociado a un departamento"
    )
    @ApiResponse(description = "Empleado recién creado",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Empleado.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "nombre": "Juan Pérez",
                                        "cargo": "Desarrollador Senior",
                                        "salario": 3500.0,
                                        "departamento": {
                                            "id": 1,
                                            "nombre": "Desarrollo",
                                            "presupuesto": 50000.0,
                                            "listaEmpleados": []
                                        },
                                        "historialFichajes": []
                                    }
                                """)
                    }
            )
    )
    @PostMapping
    public ResponseEntity<Empleado> addEmployer(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Empleado a crear", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateEmpleadoRequest.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Juan Pérez",
                                        "cargo": "Desarrollador Senior",
                                        "salario": 3500.0,
                                        "idDepartamento": 1
                                    }
                                """)
                    )
            )
            @Valid @RequestBody UpdateEmpleadoRequest empleado
    ) {

        return ResponseEntity.status(201)
                .body(empleadoService.addEmployer(empleado));

    }

    @Operation(
            summary = "Registrar un fichaje de un empleado",
            description = "Permite registrar un nuevo fichaje (alternando entre ENTRADA y SALIDA) para un empleado si se proporciona su ID"
    )
    @ApiResponse(description = "Fichaje recién creado",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetFichajeResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "fechaAccion": "2026-10-23T08:00:00",
                                        "tipoFichaje": "ENTRADA",
                                        "nombreEmpleado": "Juan Pérez"
                                    }
                                """)
                    }
            )
    )
    @PostMapping("/tracker/{id}")
    public ResponseEntity<GetFichajeResponse> addTrackEmployer(
            @Parameter(description = "Identificador del empleado a fichar") @PathVariable Long id
    ) {

        return ResponseEntity.status(201)
                .body(fichajeService.addFichaje(empleadoService.findEmployerById(id)));

    }

    @Operation(
            summary = "Editar un empleado",
            description = "Permite editar la información de un empleado si se proporciona su ID"
    )
    @ApiResponse(description = "Empleado editado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetEmpleadoResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "nombre": "Juan Pérez",
                                        "cargo": "Tech Lead",
                                        "salario": 4000.0,
                                        "nombreDepartamento": "Desarrollo",
                                        "listFichaje": []
                                    }
                                """)
                    }
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<GetEmpleadoResponse> updateEmpleado(
            @Parameter(description = "Identificador del empleado a editar") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos a editar en el empleado", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateEmpleadoRequest.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Juan Pérez",
                                        "cargo": "Tech Lead",
                                        "salario": 4000.0,
                                        "idDepartamento": 1
                                    }
                                """)
                    )
            )
            @Valid @RequestBody UpdateEmpleadoRequest empleadoDTO
    ) {

        return ResponseEntity.status(200)
                .body(empleadoService.updateEmpleado(id, empleadoDTO));

    }

    @Operation(
            summary = "Cambiar el departamento de un empleado",
            description = "Permite asignar un empleado a un nuevo departamento proporcionando el ID del empleado y el ID del departamento"
    )
    @ApiResponse(description = "Empleado con el departamento modificado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetEmpleadoResponse.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "nombre": "Juan Pérez",
                                        "cargo": "Tech Lead",
                                        "salario": 4000.0,
                                        "nombreDepartamento": "Recursos Humanos",
                                        "listFichaje": []
                                    }
                                """)
                    }
            )
    )
    @PutMapping("/{idEmple}/departamento/{idDepart}")
    public ResponseEntity<GetEmpleadoResponse> updateEmpleadoDepart(
            @Parameter(description = "Identificador del empleado") @PathVariable Long idEmple,
            @Parameter(description = "Identificador del nuevo departamento") @PathVariable Long idDepart
    ) {

        return ResponseEntity.status(200)
                .body(empleadoService.updateDepartEmpleado(empleadoService.findEmployerById(idEmple),
                        departamentoService.findById(idDepart)));
    }

    @Operation(
            summary = "Eliminar un empleado",
            description = "Permite eliminar un empleado si se proporciona su ID"
    )
    @ApiResponse(description = "Respuesta correcta de empleado eliminado",
            responseCode = "204",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(
            @Parameter(description = "Identificador del empleado a eliminar") @PathVariable Long id
    ) {

        empleadoService.deleteEmployer(id);
        return ResponseEntity.noContent().build();

    }

}

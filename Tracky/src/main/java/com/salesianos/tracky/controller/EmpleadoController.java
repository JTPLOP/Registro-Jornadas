package com.salesianos.tracky.controller;


import com.salesianos.tracky.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

}

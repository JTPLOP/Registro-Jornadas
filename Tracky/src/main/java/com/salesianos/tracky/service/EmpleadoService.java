package com.salesianos.tracky.service;

import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.repository.EmpleadoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public List<Empleado> findAllEmployers() {

        List<Empleado> resultBusqueda = empleadoRepository.findAll();

        if (resultBusqueda.isEmpty())
            throw new RuntimeException();

        return resultBusqueda;

    }


    public Empleado findEmployerById (Long id) {

        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

    }

    public Empleado addEmployer (Empleado empleado) {

        empleadoRepository.save(empleado);

        return empleado;

    }

    public void deleteEmployer (Long id) {

        if (!empleadoRepository.existsById(id))
            throw new RuntimeException();

        empleadoRepository.deleteById(id);

    }





}

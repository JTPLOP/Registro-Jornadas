package com.salesianos.tracky.service;

import com.salesianos.tracky.error.invalid.InvalidEmpleadoException;
import com.salesianos.tracky.error.notfound.EmpleadoNotFoundException;
import com.salesianos.tracky.model.DTOs.empleado.GetEmpleadoResponse;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.repository.DepartamentoRepository;
import com.salesianos.tracky.repository.EmpleadoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;

    public List<Empleado> findAllEmployers() {

        List<Empleado> resultBusqueda = empleadoRepository.findAll();

        if (resultBusqueda.isEmpty())
            throw new EmpleadoNotFoundException();

        return resultBusqueda;

    }

    public Empleado findEmployerById (Long id) {

        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));

    }

    public Empleado addEmployer (UpdateEmpleadoRequest empleadoDTO) {

        Empleado empleado = empleadoDTO.to();

        Departamento departamento = departamentoRepository.findById(empleadoDTO.idDepartamento())
                .orElse(null);
        empleado.setDepartamento(departamento);

        return empleadoRepository.save(empleado);

    }

    public GetEmpleadoResponse updateEmpleado (Long id, UpdateEmpleadoRequest empleadoDTO) {

        return empleadoRepository.findById(id)
                .map( x -> {
                    x.setNombre(empleadoDTO.nombre());
                    x.setCargo(empleadoDTO.cargo());
                    x.setSalario(empleadoDTO.salario());
                    x.getDepartamento().setId(empleadoDTO.idDepartamento());
                    empleadoRepository.save(x);
                    return GetEmpleadoResponse.of(x);

                }).orElseThrow(() -> new EmpleadoNotFoundException());
    }

    public GetEmpleadoResponse updateDepartEmpleado (Empleado empleado, Departamento departamento) {

        empleado.setDepartamento(departamento);
        empleadoRepository.save(empleado);

        return GetEmpleadoResponse.of(empleado);

    }

    public void deleteEmployer (Long id) {

        if (!empleadoRepository.existsById(id))
            throw new EmpleadoNotFoundException();

        empleadoRepository.deleteById(id);

    }


}

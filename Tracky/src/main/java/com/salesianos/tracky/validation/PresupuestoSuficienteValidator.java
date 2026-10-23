package com.salesianos.tracky.validation;


import com.salesianos.tracky.model.DTOs.departamento.UpdateDepartamentoRequest;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.model.Empleado;
import com.salesianos.tracky.repository.DepartamentoRepository;
import com.salesianos.tracky.repository.EmpleadoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PresupuestoSuficienteValidator
        implements ConstraintValidator<PresupuestoSuficiente, UpdateEmpleadoRequest> {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public boolean isValid(UpdateEmpleadoRequest empleadoDTO, ConstraintValidatorContext context) {
        if (empleadoDTO == null || empleadoDTO.idDepartamento() == null) {
            return true; // @NotNull se encarga de esto
        }

        Optional<Departamento> departamento = departamentoRepository.findById(empleadoDTO.idDepartamento());
        if (departamento.isEmpty()) {
            return true; // El servicio lanzará DepartamentoNotFoundException (404)
        }

        double totalSalario = empleadoDTO.salario();
        for (Empleado empleado : departamento.get().getListaEmpleados()) {
            totalSalario += empleado.getSalario();
        }

        return totalSalario <= departamento.get().getPresupuesto();
    }
}

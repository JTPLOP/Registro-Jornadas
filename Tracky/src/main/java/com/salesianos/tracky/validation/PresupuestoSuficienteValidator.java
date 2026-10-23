package com.salesianos.tracky.validation;


import com.salesianos.tracky.model.DTOs.departamento.UpdateDepartamentoRequest;
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
        implements ConstraintValidator<PresupuestoSuficiente, UpdateDepartamentoRequest> {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public boolean isValid(UpdateDepartamentoRequest departamentoDTO, ConstraintValidatorContext context) {
        double totalSalario = 0;

        Optional<Departamento> departamento = departamentoRepository.findByNombre(departamentoDTO.nombre());

        if (departamento.isEmpty())
            return false;

        else {
            List<Empleado> listEmpleado = departamento.get().getListaEmpleados();

            if (listEmpleado.isEmpty())
                return true;

            for (Empleado empleado : listEmpleado) {

                totalSalario += empleado.getSalario();
            }

            if (totalSalario > departamento.get().getPresupuesto())
                return false;

            return true;

        }
    }
}

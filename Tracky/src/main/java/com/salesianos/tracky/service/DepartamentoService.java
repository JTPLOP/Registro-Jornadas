package com.salesianos.tracky.service;

import com.salesianos.tracky.error.notfound.DepartamentoNotFoundException;
import com.salesianos.tracky.model.DTOs.departamento.GetDepartamentoResponse;
import com.salesianos.tracky.model.DTOs.departamento.UpdateDepartamentoRequest;
import com.salesianos.tracky.model.DTOs.empleado.UpdateEmpleadoRequest;
import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    // METODOS CRUDS


    public List<Departamento> findAll() {

        List<Departamento> resulBusqueda = departamentoRepository.findAll();

        if (resulBusqueda.isEmpty())
            throw new DepartamentoNotFoundException();

        return resulBusqueda;


    }

    public Departamento findById(Long id) {

        return departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException(id));

    }

    public Departamento addDepartment (UpdateDepartamentoRequest departamentoDTO) {

        Departamento departamento = departamentoDTO.to();

        return departamentoRepository.save(departamento);

    }

    public GetDepartamentoResponse updateDepartment ( UpdateDepartamentoRequest departamentoDTO, Long id ) {

        return departamentoRepository.findById(id)
                .map(x -> {
                    x.setNombre(departamentoDTO.nombre());
                    x.setPresupuesto(departamentoDTO.presupuesto());
                    departamentoRepository.save(x);
                    return GetDepartamentoResponse.of(x);
                })
                .orElseThrow(() -> new DepartamentoNotFoundException(id));
    }

    public void deleteDepartment (Long id) {
        if (!departamentoRepository.existsById(id))
            throw new DepartamentoNotFoundException(id);

        departamentoRepository.deleteById(id);


    }


}

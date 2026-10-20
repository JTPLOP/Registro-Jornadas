package com.salesianos.tracky.service;

import com.salesianos.tracky.model.Departamento;
import com.salesianos.tracky.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    // METODOS CRUDS


    public List<Departamento> findAll() {

        List<Departamento> resulBusqueda = departamentoRepository.findAll();

        if (resulBusqueda.isEmpty())
            throw new RuntimeException();

        return resulBusqueda;


    }

    public Departamento findById(Long id) {

        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

    }

    public Departamento addDepartment (Departamento departamento) {

        departamentoRepository.save(departamento);

        return departamento;

    }

    public void deleteDepartment (Long id) {
        if (departamentoRepository.existsById(id))
            throw new RuntimeException();

        departamentoRepository.deleteById(id);


    }







}

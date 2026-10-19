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

    public Optional<Departamento> findById(Long id) {

        Optional <Departamento> resultBusqueda = findById(id);

        if (resultBusqueda.isEmpty())
            throw new RuntimeException();

        return resultBusqueda;

    }




}

package com.salesianos.tracky.repository;

import com.salesianos.tracky.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {

    Optional<Departamento> findByNombre(String nombre);


}

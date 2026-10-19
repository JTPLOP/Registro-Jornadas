package com.salesianos.tracky.repository;

import com.salesianos.tracky.model.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichajeRepository extends JpaRepository <Fichaje, Long> {
}

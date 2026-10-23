package com.salesianos.tracky.repository;

import com.salesianos.tracky.model.ENUM.TipoFichaje;
import com.salesianos.tracky.model.Fichaje;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FichajeRepository extends JpaRepository <Fichaje, Long> {

    @Query(value = """
        SELECT e.TipoFichaje
        FROM Fichaje e
        where id=?
        ORDER BY e.fechaAccion DESC
        LIMIT 1
        """, nativeQuery = true)
    Optional<TipoFichaje> encontrarUltimoFichaje(Long id);

}
